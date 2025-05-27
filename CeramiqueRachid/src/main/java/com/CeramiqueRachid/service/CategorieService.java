package com.CeramiqueRachid.service;

import com.CeramiqueRachid.repository.AdminRepository;
import com.CeramiqueRachid.repository.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategorieService {

    private final CategorieRepository categorieRepository;

}
