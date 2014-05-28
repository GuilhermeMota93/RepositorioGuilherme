package Objects_General;

public class Funcionario {
	String name, email;
	int userid;
	
	public Funcionario(){
		
	}
	
	public Funcionario(String nome, String mail, int id){
		this.name = nome;
		this.email = mail;
		this.userid = id;
	}
	
	public String getNome(){
		return name;
	}
	
	public void setNome(String nome){
		this.name = nome;
	}
	
	public String getMail(){
		return email;
	}
	
	public void setMail(String mail){
		this.email = mail;
	}
	
	public int getID(){
		return userid;
	}
	
	public void setID(int id){
		this.userid = id;
	}
	
}