package br.edu.ifsp.arq.tsi.arqweb2.financeManager.config;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dao.*;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service.*;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.*;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.*;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.handlers.*;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.DataSourceSearcher;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import javax.sql.DataSource;

public class AppBootstrapper implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        var dataSource = DataSourceSearcher.getInstance().getDataSource();

        registerDaos(dataSource);
        registerServices();
        registerHandlers();
    }

    private void registerDaos(DataSource dataSource) {

        var userDao = new UserDao(dataSource);
        var categoryDao = new FinancialRecordCategoryDao(dataSource, userDao);
        var financialRecordDao = new FinancialRecordDao(dataSource, categoryDao, userDao);

        Bootstrapper.addDao(IFinancialRecordDao.class, financialRecordDao);
        Bootstrapper.addDao(IFinancialRecordCategoryDao.class, categoryDao);
        Bootstrapper.addDao(IUserDao.class, userDao);
    }

    private void registerServices() {

        var financialRecordDao = Bootstrapper.resolve(IFinancialRecordDao.class);
        var categoryDao = Bootstrapper.resolve(IFinancialRecordCategoryDao.class);
        var userDao = Bootstrapper.resolve(IUserDao.class);

        var boardService = new BoardService(financialRecordDao, categoryDao);
        var categoriesService = new CategoriesService(categoryDao);
        var financialRecordService = new FinancialRecordsService(financialRecordDao, categoryDao);
        var indexService = new IndexService();
        var usersService = new UsersService(userDao);

        Bootstrapper.addService(IBoardService.class, boardService);
        Bootstrapper.addService(ICategoriesService.class, categoriesService);
        Bootstrapper.addService(IFinancialRecordsService.class, financialRecordService);
        Bootstrapper.addService(IIndexService.class, indexService);
        Bootstrapper.addService(IUsersService.class, usersService);
    }

    private void registerHandlers() {

        var boardService = Bootstrapper.resolve(IBoardService.class);
        var categoriesService = Bootstrapper.resolve(ICategoriesService.class);
        var financialRecordService = Bootstrapper.resolve(IFinancialRecordsService.class);
        var indexService = Bootstrapper.resolve(IIndexService.class);
        var usersService = Bootstrapper.resolve(IUsersService.class);

        Bootstrapper.addHandler(new BoardHandler(boardService));
        Bootstrapper.addHandler(new CategoriesHandler(categoriesService));
        Bootstrapper.addHandler(new FinancialRecordsHandler(financialRecordService));
        Bootstrapper.addHandler(new IndexHandler(indexService));
        Bootstrapper.addHandler(new UsersHandler(usersService));
    }
}