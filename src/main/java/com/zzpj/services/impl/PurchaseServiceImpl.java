package com.zzpj.services.impl;


import com.zzpj.entities.Purchase;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.PurchaseRepository;
import com.zzpj.services.interfaces.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PurchaseServiceImpl extends BaseServiceImpl<PurchaseRepository, Purchase> implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        super(purchaseRepository);
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase add(Purchase purchase) {
        purchase.setVersion(0L);
        // wartość zamówienia powinna być obliczana
        purchase.setPrice(new BigDecimal(0));
        return this.purchaseRepository.save(purchase);
    }

    @Override
    public Purchase findById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(() -> entityNotFoundException(id));
    }

    public Purchase update(Purchase purchase) {
        Purchase purchaseFromRepo = purchaseRepository.findById(purchase.getId())
                .orElseThrow(() -> entityNotFoundException(purchase.getId()));
        purchase.setVersion(purchaseFromRepo.getVersion());
        return purchaseRepository.save(purchase);
    }


    private EntityNotFoundException entityNotFoundException(Long id) {
        return new EntityNotFoundException("Purchase with id " + id + " not found.");
    }
}

