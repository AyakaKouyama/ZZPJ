package com.zzpj.services.interfaces;


import com.zzpj.entities.Purchase;
import com.zzpj.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public interface PurchaseService extends BaseService<PurchaseRepository, Purchase> {

    Purchase add(Purchase purchase);

    Purchase findById(Long id);

    Purchase update(Purchase purchase);
}
