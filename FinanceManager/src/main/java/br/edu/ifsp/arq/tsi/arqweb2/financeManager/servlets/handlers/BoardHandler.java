package br.edu.ifsp.arq.tsi.arqweb2.financeManager.servlets.handlers;

import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.contracts.services.IBoardService;
import br.edu.ifsp.arq.tsi.arqweb2.financeManager.model.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardHandler implements IHandler{

    private final IBoardService boardService;

    public BoardHandler() {
        this.boardService = new BoardService();
    }

    @Override
    public Object handle(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if(request.getParameter("action").equals("preview"))
            return boardService.handlePreview(request, response);

        throw new IllegalArgumentException("Ação inválida: " + request.getParameter("action"));
    }
}