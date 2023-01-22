package com.orderservice.app.controller;

import java.io.FileNotFoundException;

import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.app.service.PdfService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/pdf")
public class PdfController {

	@Autowired
	private PdfService pdfService;
	
	
	Logger logger = LoggerFactory.getLogger(PdfController.class);
	
	@GetMapping("/getpdf/{orderId}")
	@ApiOperation("This Api is used for the get Invoice by OrderId")
	public ResponseEntity<?> getInvoicePdf(@PathVariable("orderId") String orderId) throws FileNotFoundException {
		
		//First create the pdf
		pdfService.generatePdf(orderId);
		
		return pdfService.downloadFile("Invoice1.pdf");
	}
}
