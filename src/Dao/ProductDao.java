/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Pojo.Buyer;
import Pojo.Product;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author Dell
 */
public class ProductDao {
    public boolean saveProduct(Product p){
        Configuration con=new Configuration().configure().addAnnotatedClass(Product.class);
		SessionFactory sf=con.buildSessionFactory();
		Session ss=sf.openSession();
		Transaction tx=ss.beginTransaction();
                try{
                    ss.save(p);
                    tx.commit();
                }
                catch(Exception e){
                    return false;
                }
            return true;
    }
    public Product getProduct(int id){
        Configuration con=new Configuration().configure().addAnnotatedClass(Product.class);
	SessionFactory sf=con.buildSessionFactory();
	Session ss=sf.openSession();
	Transaction tx=ss.beginTransaction();
        Query q = ss.createQuery("from Product where id =:id");
        q.setParameter("id", id);
        
        Product p = (Product) q.uniqueResult();
       if(p == null)
            return null;
        else{
            return p;
        }
    
    }
    public boolean deleteProduct(int id){
        Configuration con=new Configuration().configure().addAnnotatedClass(Product.class);
	SessionFactory sf=con.buildSessionFactory();
	Session ss=sf.openSession();
	Transaction tx=ss.beginTransaction();
        Query q= ss.createQuery("from Product where id =:id");
        q.setParameter("id", id);
        Product p1= (Product)q.uniqueResult();
        
        try{
            ss.delete(p1);
        }catch(Exception e){
            return false;
        }
        return true;
    }
    public List<Product> getProducts(){
        Configuration con=new Configuration().configure().addAnnotatedClass(Product.class);
	SessionFactory sf=con.buildSessionFactory();
	Session ss=sf.openSession();
	Transaction tx=ss.beginTransaction();
        Query q= ss.createQuery("from Product");
        
        List<Product> prods = q.list();
        return prods;
        
    }
    
    public boolean updateProduct(Product p , int id)
    {
        Configuration con=new Configuration().configure().addAnnotatedClass(Product.class);
	SessionFactory sf=con.buildSessionFactory();
	Session ss=sf.openSession();
	Transaction tx=ss.beginTransaction();
        Query q= ss.createQuery("from Product where id =:id");
        q.setParameter("id", id);
        Product p1= (Product)q.uniqueResult();
        
        p1.setId(id);
        p1.setName(p.getName());
        p1.setCategory(p.getCategory());
        p1.setDescription(p.getDescription());
        p1.setPrice(p.getPrice());
        
            ss.update(p1);
            tx.commit();
       
             return true;   
        
    }
    public int getLastId(){
        Configuration con=new Configuration().configure().addAnnotatedClass(Product.class);
	SessionFactory sf=con.buildSessionFactory();
	Session ss=sf.openSession();
	Transaction tx=ss.beginTransaction();
        Query q= ss.createQuery("select max(id) from Product ");
        try{
            int id =(int) q.uniqueResult();
           System.out.println(id);
            return id;
        }
        catch(java.lang.NullPointerException e){
            return 0;
        }
    }
}
