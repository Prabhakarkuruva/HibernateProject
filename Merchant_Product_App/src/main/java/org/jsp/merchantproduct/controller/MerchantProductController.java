package org.jsp.merchantproduct.controller;

import java.util.*;
import javax.persistence.*;

import org.jsp.merchantproduct.dao.MerchantDao;
import org.jsp.merchantproduct.dao.ProductDao;
import org.jsp.merchantproduct.dto.Merchant;
import org.jsp.merchantproduct.dto.Product;

public class MerchantProductController {
	
	static Scanner sc=new Scanner(System.in);
	static MerchantDao mdao=new MerchantDao();
	static ProductDao pdao=new ProductDao();
	
	public static void main(String[] args) {
		System.out.println("1.Save Merchant");
		System.out.println("2.Update Merchant");
		System.out.println("3.Find Merchant By Id");
		System.out.println("4.Verify  Merchant By Email id and password");
		System.out.println("5.verify Merchant By Phone and password");
		System.out.println("6.Add Product");
		System.out.println("7.Update Product");
		System.out.println("8.Find Products By Merchant id");
		System.out.println("9.Find Products By brand");
		System.out.println("10.Find Products by category");
		System.out.println("Enter Your Choice??");
		int choice=sc.nextInt();
		switch (choice) {
		case 1: 
			saveMerchant();
			break;
		case 2: 
			updateMerchant();
			break;
		case 3: 
			findMerchantById();
			break;
		case 4: 
			verifyMerchantByEmailIdAndPassword();
			break;
		case 5: 
			verifyMerchantByPhoneAndPassword();
			break;
		case 6: 
			addProduct();
			break;
		case 7: 
			updateProduct();
			break;
		case 8: 
			findProductsByMerchantId();
			break;
		case 9:
			findProductsByBrand();
			break;
			
		case 10:
			findProductsByCategory();
			break;



		default:System.err.println("Guludu ");
			break;
		}
		
	}


	private static void saveMerchant() 
	{
		System.out.println("Enter Merchant info to save-name,phone,gst_num,email,password");
		String name=sc.next();
		long phone=sc.nextLong();
		String gst_num=sc.next();
		String email=sc.next();
		String password=sc.next();
		
		Merchant m=new Merchant();
		m.setName(name);
		m.setPhone(phone);
		m.setGst_num(gst_num);
		m.setEmail(email);
		m.setPassword(password);
		Merchant mbd=mdao.saveMerchant(m);
		System.out.println("Merchant is saved with an id "+mbd.getId());
		
	}
	
	private static void updateMerchant() {
		System.out.println("Enter Merchant id ");
		
		System.out.println("Enter Merchant-name,phone,gst_num,email,password ");
		Merchant m=new Merchant();
		m.setId(sc.nextInt());
		m.setName(sc.next());
		m.setPhone(sc.nextLong());
		m.setGst_num(sc.next());
		m.setEmail(sc.next());
		m.setPassword(sc.next());
		Merchant mdb=mdao.updateMerchant(m);
		if(mdb!=null) {
			System.out.println("Merchant is upadted");
		}
		else {
			System.err.println("Merchant not upadeted since id is invalid ");
		}
		
	}
	
	
	private static void findMerchantById() 
	{
		System.out.println("Enter Merchant id to find merchant");
		int mid=sc.nextInt();
		Merchant mdb=mdao.findMerchantById(mid);
		if(mdb!=null) {
			System.out.println(mdb);
		}
		else {
			System.err.println("No merchant info found since id is invalid");
		}
	}
	
	private static void verifyMerchantByEmailIdAndPassword() 
	{
		System.out.println("Enter the emailid ??");
		String em=sc.next();
		System.out.println("Enter the password??");
		String pw=sc.next();
		
		Merchant mdb=mdao.verifyMerchantByEmailIdAndPassword(em,pw);
		if(mdb!=null) {
			System.out.println("Merchant is verfied ");
		}
		else {
			System.err.println("invalid credentials ");
		}
	}
	
	private static void verifyMerchantByPhoneAndPassword() 
	{
		System.out.println("Enter the merchnat Phone ");
		long mphone=sc.nextLong();
		System.out.println("Enter the password");
		String pw=sc.next();
		Merchant mdb=mdao.verifyMerchantByPhoneAndPassword(mphone,pw);
		if(mdb!=null) {
			System.out.println("Merchant is verified");
		}
		else {
			System.err.println("invaild credentials");
		}
	}
	
	private static void addProduct() 
	{
		System.out.println("Enter merchant id to add the product ");
		int mid=sc.nextInt();
		System.out.println("Enter product information--> name,brand ,category ");
		Product p=new Product();
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		Product pdb=pdao.addProduct(mid,p);
		if(pdb!=null) {
			System.out.println("Product has been added to database");
		}
		else {
			System.err.println("Product is not added since mid is invalid ");
		}
		
		
	}
	
	private static void updateProduct() 
	{
		System.out.println("Enter the product id ");
		System.out.println("Enter Product -name,brand,category");
		Product p=new Product();
		p.setId(sc.nextInt());
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		Product pdb=pdao.updateProduct(p);
		if(pdb!=null) {
			System.out.println("Product is upadted");
		}
		else {
			System.err.println("Product not upadted");
		}
	}
	
	
	private static void findProductsByMerchantId() 
	{
		System.out.println("Enter Merchnat id to find products ");
		int mid=sc.nextInt();
		List<Product> lpdb=pdao.findProductsByMerchantId(mid);
		if(lpdb.size()>0) {
			for (Product pro : lpdb) {
				System.out.println(pro);
			}
		}
		else {
			System.err.println("No Product Information found Merchant is is invalid");
		}
	}
	private static void findProductsByBrand() 
	{
		System.out.println("Enter the Brand name of the products  to find Products");
		//Sony and SONY both are same no problem
		String brand=sc.next();
		//Return the list of products from Dao class
		List<Product>lps=pdao.findProductsByBrand(brand);
		if(lps.size()>0)
		{
			for (Product pros : lps) {
				System.out.println(pros);
				
			}
		}
		else
		{
			System.out.println("No Products found since you have enetered invalid brand name");
		}
	}
	private static void findProductsByCategory() 
	{
		System.out.println("Enter the Product category to find Products");
		String category=sc.next();
		List<Product>lps=pdao.findProductsByCategory(category);
		if(lps.size()>0)
		{
			for (Product pros : lps) {
				System.out.println(pros);
				
			}
		}
		else
		{
			System.err.println("No Products found since you have enetered invalid category name");
		}
	}
}
