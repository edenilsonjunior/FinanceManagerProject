package br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OracleDataSourceSearcher {

    private static OracleDataSourceSearcher instance = new OracleDataSourceSearcher();

    private DataSource dataSource;

    private OracleDataSourceSearcher() {
        try {
            Context context = new InitialContext();
            context = (Context) context.lookup("java:comp/env");
            dataSource = (DataSource) context.lookup("jdbc/personal_finance_system_Oracle_DB");
        } catch (NamingException e) {
            throw new RuntimeException("Erro durante o lookup", e);
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public static OracleDataSourceSearcher getInstance() {
        return instance;
    }
}