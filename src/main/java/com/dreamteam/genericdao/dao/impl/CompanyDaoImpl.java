package com.dreamteam.genericdao.dao.impl;

import com.dreamteam.genericdao.domain.Company;

/**
 * Extending the interface Generic Dao with concrete generic Company
 *
 * @author Anna S. Almielka
 */

public class CompanyDaoImpl extends GenericDaoImpl<Company> {
    public CompanyDaoImpl() {
        super(Company.class);
    }
}
