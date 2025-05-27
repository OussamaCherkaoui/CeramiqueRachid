package com.CeramiqueRachid.service;

import com.CeramiqueRachid.repository.AdminRepository;
import com.CeramiqueRachid.repository.CommandeProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandeProduitService {

    private final CommandeProduitRepository commandeProduitRepository;

}
