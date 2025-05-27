package com.CeramiqueRachid.service;

import com.CeramiqueRachid.repository.AdminRepository;
import com.CeramiqueRachid.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandeService {

    private final CommandeRepository commandeRepository;

}
