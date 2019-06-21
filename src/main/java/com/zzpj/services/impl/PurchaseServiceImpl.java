package com.zzpj.services.impl;

import com.zzpj.dtos.BuyerDto;
import com.zzpj.dtos.OrderDto;
import com.zzpj.dtos.OrderResponseDto;
import com.zzpj.dtos.OrderStatusDto;
import com.zzpj.dtos.OrderedBookDto;
import com.zzpj.dtos.PayUToken;
import com.zzpj.dtos.PaymentStatusPayUDto;
import com.zzpj.dtos.ProductDto;
import com.zzpj.dtos.PurchaseDto;
import com.zzpj.dtos.UserDetailsDto;
import com.zzpj.entities.OrderedBook;
import com.zzpj.entities.PaymentStatus;
import com.zzpj.entities.Purchase;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.entities.User;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.exceptions.InvalidPayUSignatureException;
import com.zzpj.exceptions.InvalidPaymentException;
import com.zzpj.repositories.OrderedBookRepository;
import com.zzpj.repositories.PaymentStatusRepository;
import com.zzpj.repositories.PurchaseRepository;
import com.zzpj.repositories.ShippingMethodRepository;
import com.zzpj.repositories.UserRepository;
import com.zzpj.services.interfaces.OrderedBookService;
import com.zzpj.services.interfaces.PaymentService;
import com.zzpj.services.interfaces.PaymentStatusService;
import com.zzpj.services.interfaces.PurchaseService;
import com.zzpj.services.interfaces.ShippingMethodService;
import com.zzpj.services.interfaces.UserDetailsService;
import com.zzpj.services.interfaces.UserService;
import com.zzpj.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class PurchaseServiceImpl extends BaseServiceImpl<PurchaseRepository, Purchase, PurchaseDto> implements
        PurchaseService {

    private PaymentStatusService paymentStatusService;
    private OrderedBookService orderedBookService;
    private UserService userService;
    private UserDetailsService userDetailsService;
    private PaymentService paymentService;
    private ShippingMethodService shippingMethodService;
    private ShippingMethodRepository shippingMethodRepository;
    private PaymentStatusRepository paymentStatusRepository;
    private OrderedBookRepository orderedBookRepository;
    private UserRepository userRepository;
    private PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(
            PurchaseRepository repository,
            @Lazy OrderedBookService orderedBookService,
            UserService userService,
            UserDetailsService userDetailsService,
            PaymentService paymentService,
            PaymentStatusService paymentStatusService,
            ShippingMethodService shippingMethodService,
            ShippingMethodRepository shippingMethodRepository,
            PaymentStatusRepository paymentStatusRepository,
            OrderedBookRepository orderedBookRepository,
            UserRepository userRepository,
            PurchaseRepository purchaseRepository,
            ModelMapper modelMapper) {
        super(repository, modelMapper);
        this.orderedBookService = orderedBookService;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.paymentService = paymentService;
        this.paymentStatusService = paymentStatusService;
        this.shippingMethodService = shippingMethodService;
        this.shippingMethodRepository = shippingMethodRepository;
        this.paymentStatusRepository = paymentStatusRepository;
        this.orderedBookRepository = orderedBookRepository;
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public PurchaseDto convertToDto(Purchase entity) {
        PurchaseDto bookDto = modelMapper.map(entity, PurchaseDto.class);
        return bookDto;
    }

    @Override
    public Purchase convertToEntity(PurchaseDto dto) {
        Purchase purchase = modelMapper.map(dto, Purchase.class);
        return purchase;
    }

    @Override
    public PurchaseDto add(PurchaseDto dto, String login) {
        Purchase purchase = convertToEntity(dto);
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        PaymentStatus waitingForPayment = paymentStatusService.getPaymentStatusByName(Constants.WAITING_FOR_PAYMENT_STATUS);
        ShippingMethod shippingMethod = shippingMethodRepository.findById(dto.getShippingMethod().getId())
                .orElseThrow(() -> new EntityNotFoundException("Shipping not found"));
        purchase.setUser(user);
        purchase.setPaymentStatus(waitingForPayment);
        purchase.setShippingMethod(shippingMethod);
        purchase.setPrice(purchase.getShippingMethod().getPrice());
        return convertToDto(repository.save(purchase));
    }


    @Override
    public OrderResponseDto createOrder(Long purchaseId, String login, String ip) {
        User user = userService.findByLogin(login);
        UserDetailsDto userDetails = userDetailsService.findById(user.getId());
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Purchase with id " + purchaseId + " not found."));

        OrderDto orderDto;
        orderDto = buildOrder(user, userDetails, purchase, ip);
        PayUToken token = paymentService.getToken(Constants.PAYU_CREDENTIALS);
        paymentService.createOrder(token.getAccessToken(), token.getTokenType(), orderDto);

        OrderResponseDto orderResponseDto = paymentService.createOrder(token.getAccessToken(),
                token.getTokenType(),
                orderDto);

        purchase.setOrderId(orderResponseDto.getOrderId());
        purchaseRepository.saveAndFlush(purchase);
        return orderResponseDto;
    }

    @Override
    public void confirmPayment(Long purchaseId, String requestBody, String payUSignature) {
        if (requestBody != null && payUSignature != null) {
            if (!verifySignature(payUSignature, requestBody)) {
                throw new InvalidPayUSignatureException("PayU signature is invalid");
            }
        }
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Purchase with id " + purchaseId + " not found."));

        if (purchase.getOrderId() == null) {
            throw new InvalidPaymentException("Purchase has not been paid yet.");
        }

        PayUToken token = paymentService.getToken(Constants.PAYU_CREDENTIALS);
        PaymentStatusPayUDto paymentStatusDto = paymentService.getPaymentStatus(purchase.getOrderId(),
                token.getAccessToken(),
                token.getTokenType());

        for (OrderStatusDto status : paymentStatusDto.getOrders()) {
            if (status.getStatus().equals("COMPLETED")) {
                PaymentStatus paidStatus = paymentStatusService.getPaymentStatusByName(Constants.PAID_STATUS);
                purchase.setPaymentStatus(paidStatus);
                purchaseRepository.saveAndFlush(purchase);
            } else {
                throw new InvalidPaymentException("Error during processing purchase with id " + purchase.getId() + " has occurred.");
            }
        }
    }

    private OrderDto buildOrder(User user, UserDetailsDto userDetails, Purchase purchase, String ip) {
        OrderDto orderDto = new OrderDto();
        List products = new ArrayList();
        orderDto.setProducts(products);

        orderDto.setContinueUrl(Constants.CONTINUE_URL + purchase.getId());
        orderDto.setCustomerIp(ip);
        orderDto.setNotifyUrl(Constants.NOTIFY_URL + purchase.getId() + "/confirmPayment");
        orderDto.setMerchantPosId(Constants.MERCHENDANT_POS_ID);
        orderDto.setDescription(Constants.ORDER_DESCRIPTION);
        orderDto.setCurrencyCode(Constants.DEFAULT_CURRENCY);
        int totalAmount = purchase.getPrice().multiply(new BigDecimal(100)).intValue();
        orderDto.setTotalAmount(Integer.toString(totalAmount));
        orderDto.setNotifyUrl(Constants.NOTIFY_URL);

        for (OrderedBookDto orderedBook : orderedBookService.findByPurchaseId(purchase.getId())) {
            ProductDto product = new ProductDto();
            product.setName(orderedBook.getBook().getTitle());
            product.setQuantity(1);
            int unitPrice = orderedBook.getBook().getPrice().multiply(new BigDecimal(100)).intValue();
            product.setUnitPrice(Integer.toString(unitPrice));
            orderDto.getProducts().add(product);
        }

        BuyerDto buyer = new BuyerDto();
        buyer.setEmail(user.getEmail());
        buyer.setPhone(userDetails.getPhoneNumber());
        buyer.setFirstName(userDetails.getFirstName());
        buyer.setLastName(userDetails.getLastName());

        orderDto.setBuyer(buyer);

        return orderDto;
    }

    private boolean verifySignature(String signature, String requestBody) {
        String beginWith = "signature=";
        String endsWith = ";";
        Pattern pattern = Pattern.compile(Pattern.quote(beginWith) + "(.*?)" + Pattern.quote(endsWith));
        Matcher matcher = pattern.matcher(signature);

        String foundSignature = null;
        while (matcher.find()) {
            foundSignature = matcher.group();
        }

        String concat = requestBody + Constants.PAYU_SECOND_KEY;
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
        messageDigest.update(concat.getBytes());
        byte[] digest = messageDigest.digest();
        String expectedValue = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return expectedValue.equals(foundSignature);
    }

    @Override
    public PurchaseDto uploadTotalPrice(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Purchase with id " + purchaseId + " not found."));

        BigDecimal totalPrice = purchase.getPrice();
        List<OrderedBook> orderedBookList = orderedBookRepository.findByPurchaseId(purchase.getId());

        for (OrderedBook book : orderedBookList) {
            totalPrice = totalPrice.add(book.getBook().getPrice());
        }

        purchase.setPrice(totalPrice);
        purchaseRepository.saveAndFlush(purchase);
        return convertToDto(purchase);
    }
}

