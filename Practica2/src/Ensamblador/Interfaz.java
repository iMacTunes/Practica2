package Ensamblador;
//Librerias
import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
public class Interfaz extends javax.swing.JFrame {

    LinkedList pila = new LinkedList(); // Validar END del final
    LinkedList cola = new LinkedList(); // Validar Codop en Tabop
    int renglones,inicial;
    public Interfaz() {
        renglones=0;inicial=0;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtarea = new javax.swing.JTextArea();
        boton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ensamblador (Practica 2)");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtarea.setEditable(false);
        txtarea.setColumns(20);
        txtarea.setRows(5);
        jScrollPane1.setViewportView(txtarea);

        boton.setText("Examinar");
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonMouseEntered(evt);
            }
        });
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(boton, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(boton)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //EVENTO DE CLICK
    public void botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActionPerformed
         JFileChooser selector = new JFileChooser(); // creacion de Examinar
         txtarea.setText("");
         int valor = selector.showOpenDialog(this);
         if(valor==JFileChooser.APPROVE_OPTION){
             String ruta = selector.getSelectedFile().toString();
             try {
                 Leer(ruta);
             } catch (IOException ex) {}
         }
    }//GEN-LAST:event_botonActionPerformed
    // EVENTO DE PASAR EL MOUSE POR EL BOTON
    public void botonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonMouseEntered
        Existencia("Tabop.txt");
    }//GEN-LAST:event_botonMouseEntered

    // // <editor-fold defaultstate="collapsed" desc="Metodos de Archivos">  
    public void Leer(String ruta) throws FileNotFoundException,IOException{
        this.renglones =ContarRenglones(ruta);
        FileReader fr = new FileReader(ruta);
        BufferedReader buffer = new BufferedReader(fr);
        String aux; 
        while(true){
            aux = buffer.readLine();
            if(aux== null)
            {
                this.inicial=0;this.renglones=0;
                break;
            }
                
            else
            {
                this.inicial++;
                Comentario(aux);
            }
                 
        }
        if(!ValidarEnd())
           txtarea.append("Error : no se encontro END\n");
   }
    public void Existencia(String tabop){
       File archivo = new File(tabop);
       if(!archivo.exists())
           JOptionPane.showMessageDialog(null,"No se encontro un Archivo Tabop.txt!");
    }
    public int ContarRenglones(String ruta) throws IOException{
       FileReader fr = new FileReader(ruta);
       BufferedReader buffer = new BufferedReader(fr);
       int renglones=0; String Temporal;
       while(true)
       {
           Temporal = buffer.readLine();
           if(Temporal==null)
               break;
           else
               renglones++;
              
       }
       buffer.close();
        return renglones;
    }
    public void LeerTabop(String codop) throws FileNotFoundException, IOException{
        codop=codop.toUpperCase();
        FileReader fr = new FileReader("Tabop.txt");
        BufferedReader buffer = new BufferedReader(fr);
        String aux;
        String[] primertoken; // obtener el codop de la linea leida
        while(true)
        {
            aux= buffer.readLine();
            if(aux==null)
                break;
            else
            {
                primertoken=aux.split("\t");
                if(codop.equals(primertoken[0]))
                 cola.offer(aux);
            }
             
        }
        buffer.close();
    }
// // </editor-fold>  
      //Validacion de comentario
    public void Comentario(String aux) throws IOException{
        //Devuleve verdadero o falso si comentario empieza con ;
        boolean  comentario=aux.startsWith(";");
        
        if(comentario)
        {
            txtarea.append("COMENTARIO\n");
            txtarea.append("\n");
        }
        else
        {
            //valida falsse en caso de empezar con " " o \t
          if(aux.startsWith(" ")|| aux.startsWith("\t"))
          {
              Analizador(aux,false);
          }
          else
             Analizador(aux,true);
          
        }
    }
  
    public void Imprimir(String Etiqueta, String Codop , String Operando, boolean a, boolean b, boolean c , boolean d, boolean e,boolean f){
            // // <editor-fold defaultstate="collapsed" desc="Impresion de Errores y Tokens"> 
            txtarea.append("\nETIQUETA : "+ Etiqueta + "\n");
            if(!a)
                txtarea.append("ERROR : La etiqueta debe de tener una longitud maxima de 8 letras\n");
            if(!b)
                txtarea.append("ERROR : La Etiqueta debe de comenzar con una letra (a-Z)\n");
            if(!c)
                txtarea.append("ERROR : La Etiqueta debe de tener caracteres validos (a-z)(0-9) o '_'\n");
            txtarea.append("CODOP : "+ Codop + "\n");
            if(!d)
                txtarea.append("ERROR : La longitud del codigo de operacion debe de ser menor a 5\n");
            if(!e)
                txtarea.append("ERROR : El codigo de operacion solo admite letras (a-Z) y un solo '.'\n");
            if(!f)
                txtarea.append("ERROR : El codigo de operacion debe de comenzar con una letra (a-Z)\n");
            txtarea.append("OPERANDO : "+ Operando + "\n");
            //Errores de Sintaxys 
            if("null".equals(Codop))
                txtarea.append("ERROR :La linea tiene que tener Codigo de Operacion\n");
            Codop = Codop.toLowerCase();
            if(inicial<renglones && "end".equals(Codop))
                txtarea.append("ERROR : Se encontro End Pero no es el final del Archivo\n"); 
            txtarea.append("\n");// // </editor-fold>
            if(!"null".equals(Codop))
            {
                if(cola.isEmpty())
                    txtarea.append("***El Codigo de Operacion no se encuentra en el Tabop***\n");
                else
                {
                    String temp = cola.getFirst().toString();
                    if("null".equals(Operando)&& temp.contains("SI"))
                        txtarea.append("***El codop debe de tener Operando***\n");
                    if(!"null".equals(Operando)&&temp.contains("NO"))
                        txtarea.append("***El codop no debe de tener Operando***\n");
                    VaciarCola();
                }
            }
    }
    
    public void Analizador(String renglon, boolean bandera) throws IOException
    {
        String Etiqueta,Codop,Operando,temporal;
        boolean a,b,c,d,e,f;//Estados de error
        Codop codop = new Codop();
        a=true;b=true;c=true;d=true;e=true;f=true;
        Etiqueta eti= new Etiqueta();
        Operando op = new Operando();
        StringTokenizer tokens = new StringTokenizer(renglon);
        int token = tokens.countTokens();
        if(bandera)
        {
            int contador=0;
            Etiqueta ="null";Codop="null";Operando="null";//Inicializamos Variables
            while(tokens.hasMoreTokens())
            {
                contador++;
                switch(contador)
                {
                    case 1:
                        temporal = tokens.nextToken();
                        Etiqueta = eti.Etiqueta(temporal);
                        a=eti.longitud(temporal);
                        b=eti.primeraletra(temporal);
                        c=eti.caracteres(temporal);
                        break;
                    case 2 :
                        temporal = tokens.nextToken();
                        Codop = codop.Codop(temporal);
                        pila.push(Codop);
                        LeerTabop(Codop);
                        d=codop.longitud(temporal);
                        e=codop.caracteres(temporal);
                        f=codop.primercaracter(temporal);
                        break;
                    case 3:
                        temporal = tokens.nextToken("\n");
                        temporal=op.Operando(temporal);
                        Operando = op.Operando(temporal);
                        break;
                }
                
                
            }
            Imprimir(Etiqueta,Codop,Operando,a,b,c,d,e,f);
            
        }
        else
        { 
             int contador;
             if(token>=2)
                 contador=1;
             else
                 contador=1;
            Etiqueta ="null";Codop="null";Operando="null";//Inicializamos Variables
            while(tokens.hasMoreTokens())
            {
                contador++;
               // temporal = tokens.nextToken();
              
                switch(contador)
                {
                    case 1:
                        temporal = tokens.nextToken();
                        Etiqueta = eti.Etiqueta(temporal);
                        a=eti.longitud(temporal);
                        b=eti.primeraletra(temporal);
                        c=eti.caracteres(temporal);
                        break;
                    case 2 :
                        temporal = tokens.nextToken();
                        Codop = codop.Codop(temporal);
                        pila.push(Codop);
                        LeerTabop(Codop);
                        d=codop.longitud(temporal);
                        e=codop.caracteres(temporal);
                        f=codop.primercaracter(temporal);
                        
                        break;
                    case 3: 
                        temporal = tokens.nextToken("\n");
                        temporal=op.Operando(temporal);
                        Operando = op.Operando(temporal);
                        break;
                }
                
                
            }
            Imprimir(Etiqueta,Codop,Operando,a,b,c,d,e,f);
        }
    }
    //Metodo usado como auxiliar para validar que exista END
    public boolean ValidarEnd() {
        String end = pila.pop().toString();
        end= end.toUpperCase();
        return "END".equals(end);
    }
    public void VaciarCola(){
        txtarea.append("Codop\tOp\tMDD\tCM\tTBC\tTBX\tSTB\n");
        while(cola.peek()!=null)
        {
            txtarea.append(cola.poll().toString()+"\n");   
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panel;
    public javax.swing.JTextArea txtarea;
    // End of variables declaration//GEN-END:variables
}

