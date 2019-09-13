package juego;

public class Casillero {
  private int id;
  private string color;
  private bool tienePersonaje;
  private bool tieneObjeto;
  
  public int getId(){
    return id;
  }
  
  public void setId(int id){
    this.id=id;
  }
  
  public string getColor(){
    return color;
  }
  
  public void setColor(string color){
    this.color=color;
  }
  
  public bool getTienePersonaje(){
    return tienePersonaje;
  }
  
  public void setTienePersonaje(bool tienePersonaje){
    this.tienePersonaje=tienePersonaje;
  }
  
  public bool getTieneObjeto(){
    return tieneObjeto;
  }
  
  public void setTieneObjeto(bool tieneObjeto){
    this.tieneObjeto=tieneObjeto;
  }
  
  
}
