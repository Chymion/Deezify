package model;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;

public class Playlist extends ListeMusique {
	static DatabaseConnection db = null;
	public Playlist(String nom,String uti) {
		super(nom,uti);
		

	}
	/*
	public Playlist(String nom,String uti) throws Exception {
		super(nom,uti);
		String nomM=null;
		String dure=null;
		String date=null;
		String URL = null;
		String nomartiste=null;
		String image=null;
		String des=null;
		Musique m=null;
		Artiste art=null;
		String genre=null;
		try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root","com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException | SQLException e ) {
            e.printStackTrace();
        }
		 ResultSet rs = null;
	    try {
	            rs = db.displayData( "Select musique.NomMusique,Pseudo,Duree,Date,URL,artiste.NomArtiste,playlist.Image,Descriptif,NomGenreMusical from playlist natural join appartient natural join musique, composer, artiste where playlist.NomPlaylist='"+nom+"' and Album=0 AND musique.NomMusique=composer.NomMusique AND composer.NomArtiste=artiste.NomArtiste"  );
	    } catch ( SQLException e ) {
	            e.printStackTrace();
	     }
	     ResultSetMetaData resultMeta= (ResultSetMetaData) rs.getMetaData();
	     try {
			while ( rs.next() ) {
				for(int i = 1; i <=  resultMeta.getColumnCount(); i++) {
					if(i==1) nomM=(rs.getObject(i).toString());
            		if(i==2) uti=rs.getObject(i).toString();
            		if(i==3)dure=rs.getObject(i).toString();
            		if(i==4)date=rs.getObject(i).toString();
            		if(i==5)URL=rs.getObject(i).toString();
            		if(i==6)nomartiste=rs.getObject(i).toString();
            		if(i==7)image=rs.getObject(i).toString();
            		if(i==8)des=rs.getObject(i).toString();
            		if(i==9)genre=rs.getObject(i).toString();
            		
				}	
				art=new Artiste(nomartiste,image,des);
				m=new Musique(nomM, dure, date, URL,art);
				//this.listeMusique.ajoutMusique(m);
				this.listeMusique.add(m);
			}
	                    
	     } catch ( SQLException e ) {
	            e.printStackTrace();
	     }
	     //this.nbMusique=this.listeMusique.size();
	     this.utilisateur=uti;
	}*/
	public static void main(String[]args) throws Exception {
		Playlist a=new Playlist("Musique pop","Root");
		//System.out.println(a.listeMusique.get(1).artiste.nom+"  "+a.listeMusique.get(1).nomMusique+"  "+a.listeMusique.get(1).date );
		a.affiche();
		
	}

}