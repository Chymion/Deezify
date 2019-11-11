package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Musique {
	String nomMusique;
	String durée;
	String date;
	String chemin;
	static DatabaseConnection db = null;
	public Musique(String nomMusique, String durée, String date, String chemin) {
		this.nomMusique = nomMusique;
		this.durée = durée;
		this.date = date;
		this.chemin = chemin;
	}
	public String getNomMusique() {
		return nomMusique;
	}
	public void setNomMusique(String nomMusique) {
		this.nomMusique = nomMusique;
	}
	public String getDurée() {
		return durée;
	}
	public void setDurée(String durée) {
		this.durée = durée;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getChemin() {
		return chemin;
	}
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	public static DatabaseConnection getDb() {
		return db;
	}
	public static void setDb(DatabaseConnection db) {
		Musique.db = db;
	}
	public Musique(String nom) throws SQLException {
		String duree="";
        String date="";
        String chemin="";
		try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException | SQLException e ) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = db.displayData( "Select * from musique where NomMusique='"+nom+"'"  );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        ResultSetMetaData resultMeta= (ResultSetMetaData) rs.getMetaData();

        try {
			while ( rs.next() )
            	for(int i = 1; i <=  resultMeta.getColumnCount(); i++)
            		if(i==2) duree=rs.getObject(i).toString();
            		else {
            			if(i==3)date=rs.getObject(i).toString();
            			else if(i==4)chemin=rs.getObject(i).toString();
	
            		}
			             
                    
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        if(duree!="") {
        	this.nomMusique=nom;
        	this.durée=duree;
        	this.date=date;
        	this.chemin=chemin;
        	
        }
	}
	public static void main(String[]args) throws SQLException {
		Musique m=new Musique("Believer");
		System.out.println(m.getNomMusique());
		System.out.println(m.getDurée());
		System.out.println(m.getDate());
		System.out.println(m.getChemin());
	}
	

}
