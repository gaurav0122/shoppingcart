package com.orderservice.app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.orderservice.app.model.Items;
import com.orderservice.app.model.Order;
import com.orderservice.app.model.UserProfile;
import com.orderservice.app.repository.OrderRepository;


@Service
public class PdfService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private File foundFile;
	private Order order;
	private List<Items> items ;
	
	
	public String generatePdf(String orderId) throws FileNotFoundException {
		
		order = orderRepository.findById(orderId).get();
		UserProfile customerProfile = getuser(order.getCustomerId());
	//	UserProfile merchaProfile = getuser(order.get());
		items = order.getItems();
		System.out.println(items);
		String path="D:\\apdf\\Invoice1.pdf";
		PdfWriter pdfWriter= new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument);
		PdfPage pdfPage = pdfDocument.addNewPage();
		pdfDocument.setDefaultPageSize(PageSize.A4);
		
		
		float titlecolWidth[] = {400f , 150f};
		Table titleTable = new Table(titlecolWidth);
		titleTable.addCell(new Cell()
						.add(new Paragraph("Sold By : Shopitto India pvt. ltd.\n")
								.setBold())
						.setBorder(Border.NO_BORDER));
		String imagePath = "D:\\apdf\\qrcode.jpg";
		
		ImageData data = null;
		try {
			data = ImageDataFactory.create(imagePath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Image image = new Image(data);
		image.setHorizontalAlignment(HorizontalAlignment.CENTER);
		titleTable.addCell(new Cell(2,0)
				.add(image)
				.setHorizontalAlignment(HorizontalAlignment.CENTER)
				.setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setTextAlignment(TextAlignment.CENTER)
				.setBorder(Border.NO_BORDER));
		
		
		titleTable.addCell(new Cell().add(new Paragraph("Ship-from Address : "
													+ "shop no 44, shraddha complex, Western road\n"))
									.add(new Paragraph("Pune, Maharashtra,\n"+
													"412231")
											.setMarginLeft(80))
									.setItalic()
									.setFontSize(9f)
									.setBorder(Border.NO_BORDER));
		titleTable.addCell(new Cell()
				.add(new Paragraph("GSTIN - 12AAAAAAAAAA123"))
				.setBorder(Border.NO_BORDER));
		
		titleTable.addCell(new Cell()
				.add(new Paragraph("Invoice no : ABCDEFGH"))
				.setBorder(Border.NO_BORDER));
		
		float[] linetablewidth = { 650f };
		Table linetable = new Table(linetablewidth)
							.setMarginLeft(-15f)
							.setMarginRight(-15f);
		linetable.addCell(new Cell()
								.add(new Paragraph(""))
								.setBorderTop(new SolidBorder(1f))
								.setBorderBottom(Border.NO_BORDER)
								.setBorderLeft(Border.NO_BORDER)
								.setBorderRight(Border.NO_BORDER));
		
		
		// Creating a PdfCanvas object       
	      PdfCanvas canvas = new PdfCanvas(pdfPage);              
	      
	      // Initial point of the line       
	      canvas.moveTo(20, 695);              
	      
	      // Drawing the line       
	      canvas.lineTo(570, 695); 
	      
	      
	      // Closing the path stroke       
	      canvas.closePathStroke();                   
	   
		float[] buyertablewidth= {230f,200f,100f};
		Table buyerTable = new Table(buyertablewidth)
									.setMarginTop(11f);
		
		buyerTable.addCell(new Cell()
							.add(new Paragraph()
									.add(new Text("Order Id: ").setBold())
									.add(new Text(order.getOrderId())))
							.setBorder(Border.NO_BORDER));	
		Integer houseNo =order.getAddress().getHouseNumber();
		buyerTable.addCell(new Cell(5,0)
				.add(new Paragraph("Bill To.")
						.setBold())
				.add(new Paragraph(customerProfile.getFullName())
						.setBold())
				.add(new Paragraph(houseNo.toString()+", "+order.getAddress().getColonyName()+", "
									+order.getAddress().getStreetName()+", "
									+order.getAddress().getCity()+", "
									+order.getAddress().getState()+", "
									+order.getAddress().getPinCode()+"."))
				.add(new Paragraph("Phone: "+ customerProfile.getMobileNo()))
					.setBorder(Border.NO_BORDER));	

		
		buyerTable.addCell(new Cell(5,0)
				.add(new Paragraph("*Keep this invoice and "
									+ "manufacturer box for "
									+ "warranty purposes")
						.setItalic()
						.setFontSize(9f)
						.setMarginTop(12f))
				.setBorder(Border.NO_BORDER));	
		buyerTable.addCell(new Cell()
				.add(new Paragraph()
							.add(new Text("Order Date: ").setBold())
							.add(new Text(order.getOrderDate().toString()))
							.setFontSize(11f))
				.setBorder(Border.NO_BORDER));		
		buyerTable.addCell(new Cell()
				.add(new Paragraph()
							.add(new Text("Invoice Date: ").setBold())
							.add(new Text(order.getOrderDate().toString()))
							.setFontSize(11f))
				.setBorder(Border.NO_BORDER));
		buyerTable.addCell(new Cell()
				.add(new Paragraph()
						.add(new Text("PAN: ").setBold())
						.add(new Text("AKDJNDNJDE")))
				.setBorder(Border.NO_BORDER));	
		buyerTable.addCell(new Cell()
				.add(new Paragraph("\n"))
				.setBorder(Border.NO_BORDER));	
		
		
		float[] productTablewidth= {50f, 250f,50f, 100f,100f};
		Table productTable = new Table(productTablewidth);
		
		productTable.addCell(new Cell()
								.add(new Paragraph("Sr. No.")
										.setBold()
										.setMarginTop(2f)
										.setMarginBottom(2f))
						.setTextAlignment(TextAlignment.CENTER)
						.setBorderTop(new SolidBorder(1f))
						.setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER));
		productTable.addCell(new Cell()
						.add(new Paragraph("Product Title")
								.setBold()
								.setMarginTop(2f)
								.setMarginBottom(2f))
						.setTextAlignment(TextAlignment.CENTER)
						.setBorderTop(new SolidBorder(1f))
						.setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER));
		productTable.addCell(new Cell()
						.add(new Paragraph("Qty.")
								.setBold()
								.setMarginTop(2f)
								.setMarginBottom(2f))
						.setTextAlignment(TextAlignment.CENTER)
						.setBorderTop(new SolidBorder(1f))
						.setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER));
		productTable.addCell(new Cell()
						.add(new Paragraph("Rate \n (Rs.)")
								.setBold()
								.setMarginTop(2f)
								.setMarginBottom(2f))
						.setTextAlignment(TextAlignment.CENTER)
						.setBorderTop(new SolidBorder(1f))
						.setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER));
		productTable.addCell(new Cell()
						.add(new Paragraph("Total Amount\n (Rs.)")
								.setBold()
								.setMarginTop(2f)
								.setMarginBottom(2f))
						.setTextAlignment(TextAlignment.CENTER)
						.setBorderTop(new SolidBorder(1f))
						.setBorderBottom(new SolidBorder(1f))
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER));
		String a="1";
		Integer totalitems = 0;
		for(Items item :items) {
			
			Integer quantInt=item.getQuantity();
			String quantity = quantInt.toString(); 
			
			Double AmountDble= item.getPrice();
			String amount = AmountDble.toString();
					
			Double totalDble = (quantInt*AmountDble);
			String total= totalDble.toString();
			totalitems = totalitems+item.getQuantity();
			productTable.addCell(new Cell()
								.add(new Paragraph(a)
										.setMarginTop(2f)
										.setMarginBottom(2f))
						.setTextAlignment(TextAlignment.CENTER)
						.setBorderTop(new SolidBorder(0.01f))
						.setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER));
			productTable.addCell(new Cell()
							.add(new Paragraph(item.getProductName())
									.setMarginLeft(10f)
									.setMarginTop(2f)
									.setMarginBottom(2f))
							.setBorderTop(new SolidBorder(0.01f))
							.setBorderBottom(Border.NO_BORDER)
							.setBorderLeft(Border.NO_BORDER)
							.setBorderRight(Border.NO_BORDER));
			productTable.addCell(new Cell()
							.add(new Paragraph(quantity)
									.setMarginTop(2f)
									.setMarginBottom(2f))
						.setTextAlignment(TextAlignment.CENTER)
						.setBorderTop(new SolidBorder(0.01f))
						.setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER));
			productTable.addCell(new Cell()
							.add(new Paragraph( amount +"0")
									.setMarginTop(2f)
									.setMarginBottom(2f)
									.setMarginRight(20f))
						.setTextAlignment(TextAlignment.RIGHT)
						.setBorderTop(new SolidBorder(0.01f))
						.setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER));
			productTable.addCell(new Cell()
							.add(new Paragraph(total+"0")
									.setMarginTop(2f)
									.setMarginBottom(2f)
									.setMarginRight(20f))
						.setTextAlignment(TextAlignment.RIGHT)
						.setBorderTop(new SolidBorder(0.01f))
						.setBorderBottom(Border.NO_BORDER)
						.setBorderLeft(Border.NO_BORDER)
						.setBorderRight(Border.NO_BORDER));
			Integer b = Integer.parseInt(a)+1;
			a= b.toString();
		}
		
		productTable.addCell(new Cell(0,5)
				.add(new Paragraph("\n"))
				.setBorder(Border.NO_BORDER));
		productTable.addCell(new Cell()
				.add(new Paragraph())
				.setTextAlignment(TextAlignment.CENTER)
				.setBorderTop(Border.NO_BORDER)
				.setBorderBottom(new SolidBorder(1f))
				.setBorderLeft(Border.NO_BORDER)
				.setBorderRight(Border.NO_BORDER));
		
		productTable.addCell(new Cell()
					.add(new Paragraph("Total")
							.setBold()
							.setMarginTop(5f)
							.setMarginBottom(5f))
					.setTextAlignment(TextAlignment.CENTER)
					.setBorderTop(new SolidBorder(1f))
					.setBorderBottom(new SolidBorder(1f))
					.setBorderLeft(Border.NO_BORDER)
					.setBorderRight(Border.NO_BORDER));
		productTable.addCell(new Cell()
					.add(new Paragraph(totalitems.toString())
							.setMarginTop(5f)
							.setMarginBottom(5f))
					.setTextAlignment(TextAlignment.CENTER)
					.setBorderTop(new SolidBorder(1f))
					.setBorderBottom(new SolidBorder(1f))
					.setBorderLeft(Border.NO_BORDER)
					.setBorderRight(Border.NO_BORDER));
		productTable.addCell(new Cell()
					.add(new Paragraph(""))
					.setBorderTop(new SolidBorder(1f))
					.setBorderBottom(new SolidBorder(1f))
					.setBorderLeft(Border.NO_BORDER)
					.setBorderRight(Border.NO_BORDER));
		productTable.addCell(new Cell()
					.add(new Paragraph("Rs. "+ order.getAmountPaid().toString()+"0")
							.setMarginTop(5f)
							.setMarginBottom(5f))
					.setTextAlignment(TextAlignment.CENTER)
					.setBorderTop(new SolidBorder(1f))
					.setBorderBottom(new SolidBorder(1f))
					.setBorderLeft(Border.NO_BORDER)
					.setBorderRight(Border.NO_BORDER));
		
		
		Paragraph grandTotal = new Paragraph()
				.add(new Text("Grand Total           ")
						.setFontSize(16f))
				.add(new Text("Rs. " + order.getAmountPaid().toString()+"0")
						.setFontSize(16f)
						.setBold())
				.setMarginRight(15f)
				.setMarginTop(10f)
				.setTextAlignment(TextAlignment.RIGHT);
		
		Paragraph signature = new Paragraph()
				.add(new Text("\n\n\n\nAuthorized Signatory")
						.setFontSize(10f))
				.setMarginRight(15f)
				.setTextAlignment(TextAlignment.RIGHT);
		
		Paragraph ftext = new Paragraph()
									.add(new Text("Returns Policy: ").setBold())
									.add(new Text("At Shopitto we try to deliver perfectly each and every time."
											+ " But in the off-chance that you need to return "
											+ "the item, please do so with the original Brand box/price "
											+ "tag, original packing and invoice without which it will be really "
											+ "difficult for us to act on your request. Please help us in helping you. "
											+ "Terms and conditions apply")
											.setItalic()
											.setTextAlignment(TextAlignment.JUSTIFIED_ALL)
											.setFontSize(9f));
		Paragraph ftext1 = new Paragraph()
				.add(new Text("The goods sold as are intended for end user consumption and not for re-sale.")
						.setItalic()
						.setTextAlignment(TextAlignment.JUSTIFIED_ALL)
						.setFontSize(9f));
		
		Paragraph ftext2 = new Paragraph()
				.add(new Text("Contact Shopitto: 1234 567 8999 || www.shopitto.com/helpcentre")
						.setTextAlignment(TextAlignment.JUSTIFIED_ALL)
						.setFontSize(14f));
		
		document.add(new Paragraph("Invoice").setTextAlignment(TextAlignment.CENTER)
												.setFontSize(20f)
												.setMarginTop(-20f));
		
		
		
		
		document.add(titleTable);
		document.add(buyerTable);
		Integer totalItems = order.getItems().size();
		document.add(new Paragraph("Total items: " +totalItems.toString())
				.setFontSize(12f));
		document.add(productTable);
		document.add(grandTotal);
		document.add(signature);
		document.add(linetable);
		document.add(ftext);
		document.add(ftext1);
		document.add(ftext2);
		document.add(linetable);
		document.close();
		return "success";
	}
	
	
	public Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get("D:\\apdf");
         
        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file.toFile();
                return;
            }
        });
 
        if (foundFile != null) {
            return new UrlResource(foundFile.toURI());
        }
         
        return null;
    }
	
	    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
	         
	        Resource resource = null;
	        try {
	            resource = getFileAsResource(fileCode);
	        } catch (IOException e) {
	            return ResponseEntity.internalServerError().build();
	        }
	         
	        if (resource == null) {
	            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
	        }
	         
	        HttpHeaders httpHeaders = new HttpHeaders();
	        httpHeaders.add("File-Name", fileCode);
	        String contentType = "application/octet-stream";
	        //String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
	        
	        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
	        httpHeaders.add("File-Name", fileCode);
	        
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .headers(httpHeaders)
	                .header("File-Name", fileCode)
	                .body(resource);       
	    }
	    
	    private UserProfile getuser(String userId) {
	    	UserProfile profile = restTemplate.getForObject("http://profile-service/user/user1/"+userId, UserProfile.class);
	    	return profile;
	    }
}
