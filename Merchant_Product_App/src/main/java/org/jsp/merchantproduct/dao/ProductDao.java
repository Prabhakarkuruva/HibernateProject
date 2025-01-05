package org.jsp.merchantproduct.dao;

import java.util.List;
import javax.persistence.*;
import org.jsp.merchantproduct.dto.Merchant;
import org.jsp.merchantproduct.dto.Product;

public class ProductDao 
{
	EntityManagerFactory fac=Persistence.createEntityManagerFactory("dev");
	EntityManager man=fac.createEntityManager();

	public Product addProduct(int mid, Product p) 
	{
		EntityTransaction tran=man.getTransaction();
		tran.begin();
		Merchant mdb=man.find(Merchant.class, mid);
		if(mdb!=null) {
			mdb.getProducts().add(p);
			p.setMerchant(mdb);
			man.persist(p);
			tran.commit();
			return p;
		}
		else {
			return null;
		}
	}

	public Product updateProduct(Product p) 
	{
		EntityTransaction tran=man.getTransaction();
		tran.begin();
		Product pdb=man.find(Product.class, p.getId());
		if(pdb!=null) {
			pdb.setName(p.getName());
			pdb.setBrand(p.getBrand());
			pdb.setCategory(p.getCategory());
			tran.commit();
			return pdb;
			
		}
		else {
			return null;
		}
	}
	
	
	public List<Product> findProductsByMerchantId(int mid) 
	{
		 Query q=man.createQuery("select m.products from Merchan t m where m.id=?1");
		 q.setParameter(1, mid);
		 List<Product> lp=q.getResultList();
		 return lp;
		 
	}
	public List<Product> findProductsByBrand(String brand) 
	{
		Query q=man.createQuery("select p from Product p where p.brand=?1");
		//Pass the same user entered product brand from Controller class
		q.setParameter(1,brand);
		List<Product> lps=q.getResultList();
		return lps;
		
	}
	public List<Product> findProductsByCategory(String category)
	{
		Query q=man.createQuery("select p from Product p where p.category=?1");
		//Pass the same user entered product category from Controller class
		q.setParameter(1, category);
		List<Product> lps=q.getResultList();
		return lps;
		
	}



}
