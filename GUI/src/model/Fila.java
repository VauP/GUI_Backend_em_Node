package model;

import javax.swing.*;
import java.lang.reflect.*;

public class Fila <X> extends Dados {
	
	private Dados[] elemento; //private X[] elemento;
    private int      tamanhoInicial;
    private int      primeiro=0, ultimo=0, quantos=0; //vazio

    public Fila (int tamanho) throws Exception
    {
        if (tamanho<=0)
            throw new Exception ("Tamanho invalido");

        this.elemento       = new Dados [tamanho]; //this.elemento=new X [tamanho];
        this.tamanhoInicial = tamanho;
    }

    private void redimensioneSe (float fator)
    {
        // X[] novo = new X [Math.round(this.elemento.length*fator)];
    	Dados[] novo = new Dados [Math.round(this.elemento.length*fator)];
        
        int posNoThisElemento=this.primeiro, posNoNovo=0;
        for(int i=0; i<this.quantos; i++)
        {
            novo[posNoNovo]   = this.elemento[posNoThisElemento];
            posNoNovo         = (posNoNovo        +1) % novo.length;          // aumento circular
            posNoThisElemento = (posNoThisElemento+1) % this.elemento.length; // aumento circular 
        }
        
        this.elemento = novo;
        this.primeiro = 0;
        this.ultimo   = this.quantos;
    }

    private X meuCloneDeX (X x)
    {
        X ret=null;

        try
        {
            Class<?> classe         = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo           = classe.getMethod("clone",tipoDosParms);
            Object[] parms          = null;
            ret                     = (X)metodo.invoke(x,parms);
        }
        catch(NoSuchMethodException erro)
        {}
        catch(IllegalAccessException erro)
        {}
        catch(InvocationTargetException erro)
        {}

        return ret;
    }

    public void guardeUmItem (X x) throws Exception // FIFO
    {
        if (x == null)
            throw new Exception ("Falta o que guardar");

        if (this.ultimo+1==this.elemento.length) // cheia
            this.redimensioneSe (2.0F);

//        if (x instanceof Cloneable)
//            this.elemento[this.ultimo]=meuCloneDeX(x);
//        else

        this.elemento[this.ultimo] = (Dados) x;  

        this.ultimo = (this.ultimo+1) % this.elemento.length; // aumento circular

        this.quantos++;
 

    }

    public X recupereUmItem () throws Exception // FIFO
    {
        if (this.quantos==0) // vazia
            throw new Exception ("Nada a recuperar");

        X ret=null;
        if (this.elemento[this.primeiro] instanceof Cloneable)
            ret = meuCloneDeX((X)this.elemento[this.primeiro]);
        else
            ret = (X)this.elemento[this.primeiro];

        return ret;
    }
    
    public Integer recupereNota () throws Exception
    {
    	if (this.quantos==0) // vazia
            throw new Exception ("Nada a recuperar");

        int ret=0;
        ret = this.elemento[this.primeiro].getNota();

        return ret;
    }
    
    public Integer recupereRA () throws Exception
    {
    	if (this.quantos==0) // vazia
            throw new Exception ("Nada a recuperar");

        int ret=0;
        ret = this.elemento[this.primeiro].getRA();

        return ret;
    }
    
    public Integer recupereFreq () throws Exception
    {
    	if (this.quantos==0) // vazia
            throw new Exception ("Nada a recuperar");

        int ret=0;
        ret = this.elemento[this.primeiro].getFreq();

        return ret;
    }
    
    public Integer recupereCod () throws Exception
    {
    	if (this.quantos==0) // vazia
            throw new Exception ("Nada a recuperar");

        int ret=0;
        ret = this.elemento[this.primeiro].getCod();

        return ret;
    }

    public void removaUmItem () throws Exception // FIFO
    {
        if (this.quantos==0) // vazia
            throw new Exception ("Nada a remover");

        this.elemento[this.primeiro] = null;
        
        /*
        if (this.primeiro<this.elemento.length-1)
            this.primeiro++;
        else
            this.primeiro=0;
        */
        this.primeiro = (this.primeiro+1) % this.elemento.length; // aumento circular

        this.quantos--;

        if (this.elemento.length > this.tamanhoInicial &&
            this.quantos <= Math.round(this.elemento.length*0.25F))
            this.redimensioneSe (0.5F);
    }

    public boolean isCheia ()
    {
        if(this.quantos==this.elemento.length)
            return true;

        return false;
    }

    public boolean isVazia ()
    {
        if(this.quantos==0)
            return true;

        return false;
    }

    public String toString ()
    {
        String ret = (this.quantos) + " elemento(s)";
        
        if (this.ultimo!=-1)
			try {
				ret += ", sendo o primeiro "+ recupereRA() + " e " + recupereCod() + " e "+ recupereNota() + " e " +
        recupereFreq();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        return ret;
    }

    public boolean equals (Object obj)
    {
        if(this==obj)
            return true;

        if(obj==null)
            return false;

        if(this.getClass()!=obj.getClass())
            return false;

        Fila<X> fil = (Fila<X>) obj;

        if(this.ultimo!=fil.ultimo)
            return false;

        if(this.tamanhoInicial!=fil.tamanhoInicial)
            return false;

        for(int i=0 ; i<this.ultimo;i++)
            if(!this.elemento[i].equals(fil.elemento[i]))
                return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666/*qualquer positivo*/;

        ret = ret*7/*primo*/ + new Integer(this.ultimo        ).hashCode();
        ret = ret*7/*primo*/ + new Integer(this.tamanhoInicial).hashCode();

        for (int i=0; i<this.ultimo; i++)
            ret = ret*7/*primo*/ + this.elemento[i].hashCode();

        if (ret<0)
            ret=-ret;

        return ret;
    }

    // construtor de copia
    public Fila (Fila<X> modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("Modelo ausente");

        this.tamanhoInicial = modelo.tamanhoInicial;
        this.ultimo         = modelo.ultimo;

        // para fazer a copia dum vetor
        // precisa criar um vetor novo, com new
        // nao pode fazer this.elemento=modelo.element
        // pois se assim fizermos estaremos com dois
        // objetos, o this e o modelo, compartilhando
        // o mesmo vetor
        this.elemento = new Dados[modelo.elemento.length]; // this.elemento = new X [modelo.elemento.length];

        for(int i=0 ; i<modelo.elemento.length ; i++)
            this.elemento[i] = modelo.elemento[i];
    }

    public Object clone ()
    {
        Fila<X> ret=null;	

        try
        {
            ret = new Fila<X>(this);
        }
        catch(Exception erro)
        {}

        return ret;
    }
	}

