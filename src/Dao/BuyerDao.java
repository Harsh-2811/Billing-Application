/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Pojo.Buyer;
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
public class BuyerDao {
    public int AddBuyer(Buyer b)
	{
		
		Configuration con=new Configuration().configure().addAnnotatedClass(Buyer.class);
		SessionFactory sf=con.buildSessionFactory();
		Session ss=sf.openSession();
		Transaction tx=ss.beginTransaction();
                System.out.println(b.getContact_no());
                Query q= ss.createQuery("from Buyer where contact_no =:contact_no");
                q.setParameter("contact_no", b.getContact_no().trim());
                Buyer b1= (Buyer)q.uniqueResult();
                if(b1 == null)
                {
                    try{
                    ss.save(b);
                    tx.commit();
                    }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    return 0;
                }
                }
                else{
                    return 2;
                }
               
		
		return 1;
        }
    public Buyer checkbuyer(String number){
        Configuration con=new Configuration().configure().addAnnotatedClass(Buyer.class);
	SessionFactory sf=con.buildSessionFactory();
	Session ss=sf.openSession();
	Transaction tx=ss.beginTransaction();
        
        Query q= ss.createQuery("from Buyer where contact_no =:contact_no");
        q.setParameter("contact_no", number.trim());
        Buyer b1= (Buyer)q.uniqueResult();
        if(b1 == null)
            return null;
        else{
            return b1;
        }
    }
    
    public boolean updateBuyer(Buyer b,String number){
        Configuration con=new Configuration().configure().addAnnotatedClass(Buyer.class);
	SessionFactory sf=con.buildSessionFactory();
	Session ss=sf.openSession();
	Transaction tx=ss.beginTransaction();
        Query q= ss.createQuery("from Buyer where contact_no =:contact_no");
        q.setParameter("contact_no", number.trim());
        Buyer b1= (Buyer)q.uniqueResult();
        b1.setName(b.getName());
        b1.setGender(b.getGender());
        b1.setAddress(b.getAddress());
        b1.setContact_no(b.getContact_no());
        b1.setEmail(b.getEmail());
        
        try{
                    ss.update(b1);
                    tx.commit();
                    }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    return false;
           }
        return true;
    }
    
    public List<Buyer> getBuyers(){
        Configuration con=new Configuration().configure().addAnnotatedClass(Buyer.class);
	SessionFactory sf=con.buildSessionFactory();
	Session ss=sf.openSession();
	Transaction tx=ss.beginTransaction();
        Query q= ss.createQuery("from Buyer ");
        List<Buyer> buyers =q.list();
        return buyers;
    }
    
    public int deleteBuyer(String number){
        Configuration con=new Configuration().configure().addAnnotatedClass(Buyer.class);
	SessionFactory sf=con.buildSessionFactory();
	Session ss=sf.openSession();
	Transaction tx=ss.beginTransaction();
        Query q= ss.createQuery("from Buyer where contact_no =:contact_no");
        q.setParameter("contact_no", number.trim());
        Buyer b1= (Buyer)q.uniqueResult();
        try{
                    ss.delete(b1);
                    tx.commit();
                    }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    return 0;
           }
        return 1;
    }
}
