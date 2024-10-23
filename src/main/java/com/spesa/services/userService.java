package com.spesa.services;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.spesa.models.User;
import com.spesa.repositories.UserRepository;

@Service
public class userService {

	@Autowired
	UserRepository repository;
	@Autowired
	RevenueService revenueService;
	@Autowired
	ExpenseService expenseService;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	public User findById(UUID id) {
		
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
				
	}
	public double sobra(String id) {
		
		double revenueTotal = revenueService.totalRevenues(id);
		double expenseTotal = expenseService.totalExpense(id);
		
		double sobra = revenueTotal - expenseTotal;
		
		return sobra;
		
	}
	
	
	
	public User create(User user) {
		return repository.save(user);
	}

	public User update(UUID id, User user) {
		
		var user0 = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		user0.setName(user.getName());
		user0.setPassword(user.getPassword());
		user0.setUsername(user.getUsername());
		
		return repository.save(user0);
	}
	public void delete(UUID id) {
		var user = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		repository.delete(user);
	}

    public byte[] gerarPdf(UUID id) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            var user = findById(id);
          
            String id1 = id.toString();
            var expense = expenseService.getUserExpenses(id1);
            var revenue = revenueService.getUserRevenues(id1);
            double sobra = sobra(id1);
            String sobraStr = String.valueOf(sobra);
            double totalExpense = expenseService.totalExpense(id1);
            double totalRevenue = revenueService.totalRevenues(id1);
            String totalExpenseStr = String.valueOf(totalExpense);
            String totalRevenuenStr = String.valueOf(totalRevenue);
            
            var sobraParag = new Paragraph("Sobra do mês: " +sobraStr);
            var totalExpenseParag = new Paragraph("Valor total das despesas: " + totalExpenseStr);
            var totalRevenueParag = new Paragraph("Valor total das receitas: " + totalRevenuenStr);

            var expenseParag = new Paragraph("Despesa: " +expense.toString());
            var revenueParag = new Paragraph("Receitas: " + revenue.toString());
            
            document.add(new Paragraph("Usuario : " + user.getName()));
            document.add(revenueParag);
            document.add(expenseParag);
            
            document.add(totalRevenueParag);
            document.add(totalExpenseParag);
            document.add(sobraParag);
            document.close();
            
            return byteArrayOutputStream.toByteArray();
        	}
    	
    
}
