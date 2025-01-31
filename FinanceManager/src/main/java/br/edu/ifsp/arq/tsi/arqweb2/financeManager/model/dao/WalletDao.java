package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IWalletDao;

import javax.sql.DataSource;

public class WalletDao implements IWalletDao {

    private final DataSource dataSource;

    public WalletDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}