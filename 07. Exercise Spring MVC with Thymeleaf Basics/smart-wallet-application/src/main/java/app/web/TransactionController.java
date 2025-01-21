package app.web;

import app.transaction.model.Transaction;
import app.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ModelAndView getAllTransactions() {
        ModelAndView modelAndView = new ModelAndView("transactions");

        List<Transaction> transactions =
                transactionService.getAllByOwnerId(UUID.fromString("cd8e1d31-53e4-4cf1-9df6-851a32c613db"));
        modelAndView.addObject("transactions", transactions);

        return modelAndView;
    }
}
