package br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.dao.IWalletDao;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IWalletsService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.dto.*;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.FinancialRecord;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.financialRecord.TransactionTypeEnum;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.user.User;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet.Wallet;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.entity.wallet.WalletTransaction;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.utils.Utils;
import com.google.gson.JsonElement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;

public class WalletsService implements IWalletsService {

    private final IWalletDao walletDao;

    public WalletsService(IWalletDao walletDao) {
        this.walletDao = walletDao;
    }

    @Override
    public JsonElement handleHistory(HttpServletRequest request, HttpServletResponse response) {

        var user = Utils.getUser(request);

        var wallets = walletDao.getWalletsByUserId(user.getId());

        var walletsDto = new ArrayList<WalletHistory>();

        for(var wallet : wallets){

            var transactions = walletDao.getTransactionsByWalletId(wallet.getId());

            var lastTransaction = transactions
                    .stream()
                    .max(Comparator.comparing(WalletTransaction::getTransactionDate));

            walletsDto.add(getWalletHistory(wallet, lastTransaction));
        }

        var overview = walletDao.getWalletOverviewByUserId(user.getId());

        overview.setHighestBalanceWallet(
                walletsDto.stream()
                .max(Comparator.comparing(WalletHistory::currentBalance)));
        
        var content = new WalletDto(walletsDto, overview);

        return Utils.toJson(content);
    }

    @Override
    public Object handleCreate(HttpServletRequest request, HttpServletResponse response) {

        var user = Utils.getUser(request);

        var name = request.getParameter("name");
        var goalAmount = Double.parseDouble(request.getParameter("goal-amount"));
        var description = request.getParameter("description");

        var wallet = new Wallet();
        wallet.setUser(user);
        wallet.setName(name);
        wallet.setGoalAmount(goalAmount);
        wallet.setDescription(description);
        wallet.setCurrentBalance(0.00);

        if (walletDao.create(wallet) == null) {
            request.setAttribute("error", "Houve um erro ao criar a carteira");
            return "dispatcher:/create-wallet";
        }

        request.getSession(false).setAttribute("success", "Carteira criada com sucesso");
        return "/index";
    }

    @Override
    public Object handleCreateTransaction(HttpServletRequest request, HttpServletResponse response) {

        var user = Utils.getUser(request);

        var walletField = request.getParameter("wallet-id");
        var amount = request.getParameter("goal-amount");

        var transactionType = TransactionTypeEnum.valueOf(request.getParameter("transactionType"));

        var walletId = Long.parseLong(walletField);
        var goal = Double.parseDouble(amount);

        var wallet = walletDao.getWalletByIdAndUserId(walletId, user.getId());

        var transaction = new WalletTransaction();
        transaction.setAmount(goal);
        transaction.setTransactionType(transactionType);

        if (walletDao.createTransaction(wallet.getId(), transaction) == null) {
            request.setAttribute("error", "Houve um erro ao criar a transação");
            return "dispatcher:/create-wallet-transaction";
        }

        request.getSession(false).setAttribute("success", "Transação criada com sucesso");
        return "/index";
    }

    @Override
    public JsonElement handleDetails(HttpServletRequest request, HttpServletResponse response) {

        var user = Utils.getUser(request);
        var walletId = Long.parseLong(request.getParameter("wallet-id"));

        var wallet = walletDao.getWalletByIdAndUserId(walletId, user.getId());
        var transactions = walletDao.getTransactionsByWalletId(walletId);

        var transactionsDto = new ArrayList<WalletDetailsTransaction>();

        for(var transaction : transactions){
            transactionsDto.add(new WalletDetailsTransaction(
                    transaction.getTransactionType().name(),
                    transaction.getAmount(),
                    transaction.getTransactionDate()
            ));
        }

        var walletDetails = new WalletDetails(
                wallet.getName(),
                wallet.getGoalAmount(),
                wallet.getCurrentBalance(),
                wallet.getDescription(),
                transactionsDto
        );

        return Utils.toJson(walletDetails);
    }

    @Override
    public Object handleUpdate(HttpServletRequest request, HttpServletResponse response) {

        var user = Utils.getUser(request);

        var walletId = Long.parseLong(request.getParameter("wallet-id"));
        var name = request.getParameter("name");
        var goalAmount = Double.parseDouble(request.getParameter("goal-amount"));
        var description = request.getParameter("description");

        var wallet = walletDao.getWalletByIdAndUserId(walletId, user.getId());

        wallet.setName(name);
        wallet.setGoalAmount(goalAmount);
        wallet.setDescription(description);

        walletDao.update(wallet);

        return "/wallets";
    }

    @Override
    public Object handleUpdateView(HttpServletRequest request, HttpServletResponse response) {

        var user = Utils.getUser(request);

        long walletId = Long.parseLong(request.getParameter("wallet-id"));

        var wallet = walletDao.getWalletByIdAndUserId(walletId, user.getId());

        var responseContent = new HashMap<String, Object>();
        responseContent.put("wallet", wallet);

        return Utils.toJson(responseContent);
    }

    @Override
    public Object handleDelete(HttpServletRequest request, HttpServletResponse response) {

        var session = request.getSession(false);

        long walletId = Long.parseLong(request.getParameter("wallet-id"));

        if (walletDao.delete(walletId))
            session.setAttribute("success", "Carteira excluida com sucessa!");
        else
            session.setAttribute("error", "Houve um erro ao excluir a carteira!");

        return "/index";
    }

    private static WalletHistory getWalletHistory(Wallet wallet, Optional<WalletTransaction> lastTransaction) {
        Optional<WalletHistoryLastTransaction> lastTransactionDto = Optional.empty();

        if(lastTransaction.isPresent()){
            lastTransactionDto = Optional.of(new WalletHistoryLastTransaction(
                    lastTransaction.get().getTransactionType().name(),
                    lastTransaction.get().getAmount()
            ));
        }

        return new WalletHistory(
                wallet.getId(),
                wallet.getName(),
                wallet.getCurrentBalance(),
                wallet.getGoalAmount(),
                lastTransactionDto
        );
    }
}