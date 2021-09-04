/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpro;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.*;
import javax.swing.*;
/**
 *
 * @author ahmed
 */

public class TestPro  {
    static ArrayList<createGate> array=new ArrayList<>();
    static ArrayList<createGate> lede=new ArrayList<>();
    static boolean decoder=false;
    static boolean not=false;
    createGate crea;
    /**
     * @param args the command line arguments
     */
    
    void getled(createGate c){
        this.crea=c;
    }
    
    public static void main(String[] args) {
    //Frame
        JFrame form1 = new JFrame("Responsive JFrame");
        form1.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        form1.setLayout(null);
        try {
            form1.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("shape100.PNG")))));
        } catch (IOException e) {
            
        }
    ///////////
        
    //Componants
        Panel componants = new Panel();
        componants.setLayout(null);
        componants.setBounds(0, 0, 300, 1100);
        componants.setBackground(Color.LIGHT_GRAY);
        form1.add(componants);
//        createGate addarray=new createGate();
        
        Panel draw = new Panel();
        draw.setBounds(300, 0, 1650, 1200);
     //   componants.setBackground(Color.red);
        form1.add(draw);
        
        Font f = new Font("gate",Font.BOLD,15);
        
        Label gateLabel = new Label("Gates");    
        gateLabel.setBounds(130, 10, 200, 30);
        componants.add(gateLabel);
        gateLabel.setFont(f);    
        
        JButton add=new JButton();
        add.setText("turn on");
        draw.add(add);
        
        add.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
             for(int i=0;i<lede.size();i++){
               if(lede.get(i).input1==1){                 
                    lede.get(i).gate.setBackground(Color.YELLOW);
               }
               if(lede.get(i).input1==0){                 
                    lede.get(i).gate.setBackground(Color.darkGray);
                    
               }
             }
            }
        });
        element and = new element(10, 60, "and", "AND.PNG", true, componants, draw, form1);
        element or = new element(170, 60, "or", "OR.PNG", true, componants, draw, form1);
        element nand = new element(10, 190, "nand", "NAND.PNG", true, componants, draw,  form1);
        element nor = new element(170, 190, "nor", "NOR.PNG", true, componants, draw,  form1);
        element xor = new element(10, 320, "xor", "XOR.PNG", true, componants, draw,  form1);
        element xnor = new element(170, 320, "xnor", "XNOR.PNG", true, componants, draw,  form1);
        element not = new element(10, 450, "not", "NOT.PNG", true, componants, draw,  form1);
        element decoder = new element(170, 450, "decoder", "DECODER.PNG", true, componants, draw, form1);
        Label inputLabel = new Label("Input");    
        inputLabel.setBounds(130, 480, 200, 30);
        componants.add(inputLabel);
        inputLabel.setFont(f);
        element highConstant = new element(10, 630, "High Constant", "1",componants,  draw, form1);
        element lowConstant = new element(170, 630, "Low Constant", "0", componants,  draw, form1);
        
        Label outputLabel = new Label("Output");    
        outputLabel.setBounds(130, 780, 200, 30);
        componants.add(outputLabel);
        outputLabel.setFont(f);
        element led = new element(90, 550, "led", "led1.PNG", componants, draw,  form1);

        form1.setVisible(true);
        form1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static class element extends Panel {
        private Panel p;
        private Label elementName;
        private JButton componant;
        private Gates gate;

        public element(int width, int hight, String name, String value, Panel panel, Panel panel2, Frame f) {
            //panel

            this.p = new Panel();
            this.p.setLayout(null);
            this.p.setBounds(width, hight, 120, 100);

            //button
            this.componant = new JButton(value);
            componant.setBounds(30, 10, 60, 60);
            p.add(componant);

            //label
            this.elementName = new Label(name);
            this.elementName.setBounds(10, 70, 120, 30);
            this.p.add(elementName);

            //add componants
            componant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    createGate zeroOrOne = new createGate(value, 100,150, panel2,name);
                    //add=zeroOrOne;
                    if(name.equals("led")){
                        lede.add(zeroOrOne);
                    }
                    TestPro.array.add(zeroOrOne);
                    zeroOrOne.outputWire.check(TestPro.array);
                    zeroOrOne.outputWire2.check(TestPro.array);
                    zeroOrOne.outputWire3.check(TestPro.array);
                    zeroOrOne.outputWire4.check(TestPro.array);

                }  
            });
            panel.add(p);
            f.add(panel);
        }

        // ather componants
        public element(int width, int hight, String name, String source, boolean isGate, Panel panel, Panel panel2,Frame f) {
            //panel
            p = new Panel();
            p.setLayout(null);
            p.setBounds(width, hight, 120, 100);

            //button
            ImageIcon icon = new ImageIcon(source);
            this.componant = new JButton(icon);
            componant.setBounds(0, 0, 120, 70);
            if (name == "led") {
                this.componant.setSize(120, 140);
            }
            p.add(componant);

            //label
            this.elementName = new Label(name);
            this.elementName.setBounds(45, 70, 120, 30);
            p.add(elementName);

            //add componants
            componant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    createGate newGate = new createGate(name, 100, 150, panel2,name);

                    TestPro.array.add(newGate);
                    newGate.outputWire.check(TestPro.array);
                    newGate.outputWire2.check(TestPro.array);
                    newGate.outputWire3.check(TestPro.array);
                    newGate.outputWire4.check(TestPro.array);
                     if(name=="decoder"){
                        newGate.decoder=true;
                    } 
                    if(name=="not"){
                        newGate.not=true;
                    }
                }  
            });
            panel.add(p);
            f.add(panel);
        }
    }
}

class createGate extends JButton {
    int input1=-1;
    int input2=-1;
    int output=-1;
    int value=-1;
    JButton gate;
    int retur;
    String name;
    boolean decoder=false;
    boolean not=false;
    boolean decode=false;
    JButton pin1 = new JButton();
    JButton  pin2 = new JButton();
    JButton pin3 = new JButton();
    JButton  pin4 = new JButton();
    JButton pin5 = new JButton();
    JButton  pin6 = new JButton();
    wire outputWire = new wire();
    wire outputWire2 = new wire();
    wire outputWire3 = new wire();
    wire outputWire4 = new wire();
    wire inputWire1 = new wire();
    wire inputWire2 = new wire();
    boolean yes1=false;
    boolean yes2=false;
    boolean cur=false;
    Decoder_Gate an;
    JButton paa=new JButton(); 
    createGate(){
    }
    
    public createGate (String name, int x, int y, Panel panel,String nam) {
        this.name=nam;
        panel.setLayout(new BorderLayout());
        gate = new JButton();
        gate.setBounds(x, y, 120, 70);
        if(nam.equals("decoder")){
            pin3.setBounds(x+120,y+2,8,8);
            pin4.setBounds(x+120,y+22,8,8);
            pin5.setBounds(x+120,y+42,8,8);
            pin6.setBounds(x+120,y+62,8,8);
            outputWire.setBounds(x-152,y-105,2000,2000);
            outputWire2.setBounds(x-152,y-125,2000,2000);
            outputWire3.setBounds(x-152,y-145,2000,2000);
            outputWire4.setBounds(x-152,y-165,2000,2000);
            pin1.setBounds(gate.getX()-25, gate.getY()+5, 20, 20);
            pin2.setBounds(gate.getX()-25, gate.getY()+45, 20, 20);
            panel.repaint();
            panel.add(outputWire);
            panel.add(outputWire2);
            panel.add(outputWire3);
            panel.add(outputWire4);
            panel.add(pin3);
            panel.add(pin4);
            panel.add(pin5);
            panel.add(pin6);
            repaint();
            
            gate.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e) { 
                    Decoder_Gate and=new Decoder_Gate(input1,input2);
                    createGate.this.an=and;
                }
            });   
            pin3.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) { 
                    System.out.println( createGate.this.an.getOutput1());
                    System.out.println( createGate.this.an.getOutput2());
                    System.out.println( createGate.this.an.getOutput3());
                    System.out.println( createGate.this.an.getOutput4());
                    panel.remove(outputWire4);
                    panel.add(outputWire4);
                    outputWire4.addlistener();
                    decode=true;
                    if(createGate.this.an.getOutput1()==1){
                        output=createGate.this.an.getOutput1();
                        outputWire4.setvalu(output);
                        System.out.println(output);
                    }
                    else{
                        output=0;
                    }
                }
            });
            pin4.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) { 
                    System.out.println( createGate.this.an.getOutput1());
                    System.out.println( createGate.this.an.getOutput2());
                    System.out.println( createGate.this.an.getOutput3());
                    System.out.println( createGate.this.an.getOutput4());
                    panel.remove(outputWire3);
                    panel.add(outputWire3);
                    outputWire3.addlistener();
                    decode=true;
                    if(createGate.this.an.getOutput2()==1){
                        output=createGate.this.an.getOutput2();
                        outputWire3.setvalu(output);
                        System.out.println(output); 
                    } else {
                         output=0;
                    }
                }
            });
            pin5.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) { 
                    System.out.println( createGate.this.an.getOutput1());
                    System.out.println( createGate.this.an.getOutput2());
                    System.out.println( createGate.this.an.getOutput3());
                    System.out.println( createGate.this.an.getOutput4());
                    panel.remove(outputWire2);
                    panel.add(outputWire2);
                    outputWire2.addlistener();
                    decode=true;
                    if(createGate.this.an.getOutput3()==1){
                        output=createGate.this.an.getOutput3();
                        outputWire2.setvalu(output);
                        System.out.println(output); 
                    } else{
                        output=0;
                    }                 
                }
            });
            pin6.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) { 
                    System.out.println( createGate.this.an.getOutput1());
                    System.out.println( createGate.this.an.getOutput2());
                    System.out.println( createGate.this.an.getOutput3());
                    System.out.println( createGate.this.an.getOutput4());
                    panel.remove(outputWire);
                    panel.add(outputWire);
                    outputWire.addlistener();
                    decode=true;
                    if(createGate.this.an.getOutput4()==1 ){
                        output=createGate.this.an.getOutput4();
                        outputWire.setvalu(output);
                        System.out.println(output);
                    } else{
                        output=0;
                    }
                }
            });
        }
        if("not".equals(nam) ||"High Constant".equals(nam) || "Low Constant".equals(nam) || "led".equals(nam)  ){
            outputWire.setBounds(x-160,y-130,2000,2000);

            if("High Constant".equals(nam)){
                output=1;
            }
            if("Low Constant".equals(nam)){
                output=0;
            }
            if("not".equals(nam)){
    //Not_Gate n=new Not_Gate();
            }
            if(! "led".equals(nam)){
                 panel.add(outputWire);
            }
            if("led".equals(nam) ||"not".equals(nam) ){
                pin1.setBounds(gate.getX()-25, gate.getY()+30, 20, 20);
                panel.add(pin1);
                panel.repaint();
            }

            gate.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) { 
                    panel.remove(outputWire);
                    panel.add(outputWire);
                    outputWire.setvalu(output);
                    outputWire.addlistener();
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    if(name.equals("not")){
                        Not_Gate and=new Not_Gate(input1);
                        output=and.Output();
                        outputWire.setvalu(output);
                    }
                    System.out.println(output);
                }
            });   
            panel.repaint();
        }
        if(!"not".equals(nam) &&!"High Constant".equals(nam) && !"Low Constant".equals(nam) &&! "led".equals(nam) && !"decoder".equals(nam) ){
            System.out.println("innnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnniiiiiiii");  
            if(name.equals("and")){
                AND_Gate and=new AND_Gate(input1,input2);
                output=and.Output();
                outputWire.setvalu(output);
                retur=and.Output();
            }
            outputWire.setBounds(x-160,y-130,2000,2000);
            pin1.setBounds(gate.getX()-25, gate.getY()+5, 20, 20);
            pin2.setBounds(gate.getX()-25, gate.getY()+45, 20, 20);
            panel.repaint();
            panel.add(outputWire);
            gate.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) { 
                    if(name.equals("and")){
                        AND_Gate and=new AND_Gate(input1,input2);
                        output=and.Output();
                        retur=and.Output();
                    }
                    if(name.equals("or")){
                        OR_Gate and=new OR_Gate(input1,input2);
                        output=and.Output();
                        retur=and.Output();
                    }
                    if(name.equals("nand")){
                        NAND_Gate and=new NAND_Gate(input1,input2);
                        output=and.Output();
                        retur=and.Output();
                    }
                    if(name.equals("nor")){
                        NOR_Gate and=new NOR_Gate(input1,input2);
                        output=and.Output();
                        retur=and.Output();
                    }
                    if(name.equals("xor")){
                        XOR_Gate and=new XOR_Gate(input1,input2);
                        output=and.Output();
                        retur=and.Output();
                    }
                    if(name.equals("xnor")){
                        XNOR_Gate and=new XNOR_Gate(input1,input2);
                        output=and.Output();
                        retur=and.Output();
                    }
                    outputWire.setvalu(output);
                    System.out.println("output ="+output);
                    panel.remove(outputWire);
                    panel.add(outputWire);
                    outputWire.addlistener();
                }
            });
        }
        gate.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent E) {
                System.out.println(retur);
                int moveToX = E.getX() + gate.getX();
                int moveToY = E.getY() + gate.getY();
                if(nam.equals("decoder")){
                    gate.setBounds(moveToX,moveToY,120,70);
                    pin3.setBounds(moveToX+120,moveToY+2,8,8);
                    pin4.setBounds(moveToX+120,moveToY+22,8,8);
                    pin5.setBounds(moveToX+120,moveToY+42,8,8);
                    pin6.setBounds(moveToX+120,moveToY+62,8,8);
                    outputWire.setpoint(moveToX+180,moveToY+25-10);
                    outputWire2.setpoint(moveToX+180,moveToY+25-10);
                    outputWire3.setpoint(moveToX+180,moveToY+25-10);
                    outputWire4.setpoint(moveToX+180,moveToY+25-10);
                    pin1.setBounds(gate.getX()-25, gate.getY()+5, 20, 20);
                    pin2.setBounds(gate.getX()-25, gate.getY()+45, 20, 20);
                    if(yes1==true){
                       inputWire1.setpoint2(gate.getX()+35,gate.getY()-5);
                    }
                    if(yes2==true){
                        inputWire2.setpoint2(gate.getX()+35,gate.getY()+35);
                    }
                    if(outputWire.tr==false){
                      outputWire.setpoint2(moveToX+180,moveToY+25-10);
                    }
                    if(outputWire2.tr==false){
                        outputWire2.setpoint2(moveToX+180,moveToY+25-10);
                    }
                    if(outputWire3.tr==false){
                        outputWire3.setpoint2(moveToX+180,moveToY+25-10);
                    }
                    if(outputWire4.tr==false){
                        outputWire4.setpoint2(moveToX+180,moveToY+25-10);
                    }
                }
                if(nam.equals("not") ||"High Constant".equals(nam) || "Low Constant".equals(nam) || "led".equals(nam)){
                    gate.setBounds(moveToX,moveToY,120,70);
                    
                    if("led".equals(nam) ||"not".equals(nam) ){
                        pin1.setBounds(gate.getX()-25, gate.getY()+30, 20, 20);
                    }
                    
                    outputWire.setpoint(moveToX+180,moveToY+15);
                    if(yes1==true){
                       inputWire1.setpoint2(gate.getX()+35,gate.getY()+10);
                    }
                    if(yes2==true){
                        inputWire2.setpoint2(gate.getX()+35,gate.getY()+35);
                    }
                    if(outputWire.tr==false){
                        outputWire.setpoint2(moveToX+180,moveToY+15);
                    }
                } else if(!nam.equals("not") ||!"High Constant".equals(nam) ||! "Low Constant".equals(nam) ||!"led".equals(nam)) {
                    gate.setBounds(moveToX,moveToY,120,70);
                    
                    pin1.setBounds(gate.getX()-25, gate.getY()+5, 20, 20);
                    pin2.setBounds(gate.getX()-25, gate.getY()+45, 20, 20);
                    outputWire.setpoint(moveToX+180,moveToY+15);
                    
                    if(yes1==true){
                       inputWire1.setpoint2(gate.getX()+35,gate.getY()-5);
                    }
                    if(yes2==true){
                        inputWire2.setpoint2(gate.getX()+35,gate.getY()+35);
                    }
                    if(outputWire.tr==false){
                        outputWire.setpoint2(moveToX+180,moveToY+15);
                    }
                }
                panel.repaint();
            }
        });
       
        if ("High Constant".equals(nam) || "Low Constant".equals(nam)) {
            gate.setText(nam); 
        } 
        
        if (nam.equals("led")) {
            gate.setBackground(Color.DARK_GRAY);
            panel.repaint();
        } else if(!"High Constant".equals(nam) ||! "Low Constant".equals(nam) ||!nam.equals("led") ){
            gate.setIcon(setImage(name));
        }
        
        pin1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                inputWire1.tru=true;
                getpane(pin1);
                System.out.println("eb");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                inputWire1.tru=false;
            }
        });
        pin2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                inputWire2.tru=true;
                getpane(pin2);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                inputWire2.tru=false;
            }
        });
            
        panel.repaint();
        panel.add(pin1);
        panel.add(pin2); 
        panel.repaint();
        panel.add(gate);
    }
    public ImageIcon setImage (String nameOfGate) {
        ImageIcon gateImage = new ImageIcon();
        if (nameOfGate == "and")
            gateImage = new ImageIcon("AND.PNG");
        else if (nameOfGate == "or")
            gateImage = new ImageIcon("OR.PNG");
        else if (nameOfGate == "nand")
            gateImage = new ImageIcon("NAND.PNG");
        else if (nameOfGate == "nor")    
            gateImage = new ImageIcon("NOR.PNG");
        else if (nameOfGate == "xor")
            gateImage = new ImageIcon("XOR.PNG");
        else if (nameOfGate == "xnor")
            gateImage = new ImageIcon("XNOR.PNG");
        else if (nameOfGate == "not")
            gateImage = new ImageIcon("NOT.PNG");
        else if (nameOfGate == "decoder")
            gateImage = new ImageIcon("Decoder.PNG");
        return gateImage;
    }
    boolean decod=false;
    
    void getpane(JButton paa) {
        this.paa=paa;
    }
    void getpane1(wire paa) {
        this.inputWire1=paa;
    }
    void getpane2(wire paa) {
        this.inputWire2=paa;
    }   
}

//=================================================================================================================================

//wire
class wire extends JComponent {
    public ArrayList<createGate> componantInFram = new ArrayList<>();
    private int value = 0;
    private boolean  isConnect = false;
    private int mationX1;
    private int mationY1;
    private int mationX2;
    private int mationY2;
    int xc;
    int yc;
    boolean curvv=false;
    String name;
    public boolean tru=false;
    public boolean tr=false;
    
    Rectangle2D[] points = { new Rectangle2D.Double(280, 165,8, 8), new Rectangle2D.Double(280, 165,8, 8) };
    CubicCurve2D  s = new CubicCurve2D.Double();
    
    public void setvalu(int value) {
        this.value=value;
        
    }
    
    public int getvalue() {
        return value;
    }
    
    public void check(ArrayList a) {
        this.componantInFram=a;
    }
  
    wire.ShapeResizeHandler ada = new wire.ShapeResizeHandler();
    
    public void setpoint(int x,int y){
        this.mationX1=x;
        this.mationY1=y;
        points[1].setRect(x,y,8,8);
        repaint();
    }
    
    public void setpoint2(int x,int y){
        this.mationX2=x;
        this.mationY2=y;
        points[0].setRect(mationX2,mationY2,8,8);
        repaint();
    }
    
    MouseListener mlis=new MouseAdapter() {};
    MouseMotionListener mmlis=new MouseAdapter() {};
    
    public void addlistener(){
        addMouseListener(ada);
        addMouseMotionListener(ada);
    }
    
    public void curve(int xc,int yc){
        this.xc=xc;
        this.yc=yc;
    }
    
    public void delete(){
        super.removeMouseListener(ada);
        super.removeMouseMotionListener(ada);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < points.length; i++) {
            g2.fill(points[i]);
        }
        if(curvv==false){
            xc=yc=0;
        }
        
        s.setCurve(points[0].getX(), points[0].getY(),points[0].getX(), points[0].getY(),
        points[1].getX(), points[1].getY(), points[1].getX(), points[1].getY());
        g2.draw(s);
    }
    
    class ShapeResizeHandler extends MouseAdapter {
        int xxx;
        int yyy;
        
        void getposition(int xxx,int yyy) {
            this.xxx=xxx;
            this.yyy=yyy;
        }
        
        double xx,yy,hh,ww;
        boolean ch=false;
        boolean chosen=false;
        boolean pan=false;
        int v;
        
        void cheis(boolean chosen){
            this.chosen=chosen;
        } 
        
        @Override
        public void mousePressed(MouseEvent event) {
            this.xx=points[0].getX();
            this.yy=points[0].getY();
            this.hh=points[0].getHeight();
            this.ww=points[0].getWidth();
            Point p = event.getPoint();
            if (points[0].contains(p)) {
              ch=true;
            }
        }
        @Override
        public void mouseReleased(MouseEvent event) {
            ch = false;
            boolean vbn=false;
            System.out.println("enter"+value);
            delete();
            
            for(int i=0;i<wire.this.componantInFram.size();i++) {
                v=i;
                 
                if(wire.this.componantInFram.get(i).inputWire1.tru==true) {
                    
                     wire.this.componantInFram.get(i).input1=wire.this.value;
                     System.out.println( wire.this.componantInFram.get(i).input1);
                    wire.this.componantInFram.get(i).cur=true;
                    wire.this.curvv=true;
                    if(wire.this.componantInFram.get(i).paa==wire.this.componantInFram.get(i).pin1) {
                        wire.this.componantInFram.get(i).yes1=true;
                        wire.this.componantInFram.get(i).getpane1(wire.this);
                    }
                    if(wire.this.componantInFram.get(i).paa==wire.this.componantInFram.get(i).pin2 ){
                        wire.this.componantInFram.get(i).yes2=true;
                        wire.this.componantInFram.get(i).getpane2(wire.this);
                    }             
                    System.out.println(wire.this.componantInFram.get(i).decode);
                    if(wire.this.componantInFram.get(i).decode=false){
                          System.out.println("decoder------------------------------------------------------------");
                      points[0].setRect(wire.this.componantInFram.get(i).paa.getX()+60,wire.this.componantInFram.get(i).paa.getY()-20+10,ww,hh);

                    }
                     if(wire.this.componantInFram.get(i).decode=true){
                         System.out.println("//////////////////////////////////////////////////////////");
                         points[0].setRect(wire.this.componantInFram.get(i).paa.getX()+60,wire.this.componantInFram.get(i).paa.getY()-20+10,ww,hh);
                    }
                    wire.this.tr=true;
                    repaint();
                    vbn=true;
                }         
                 if(wire.this.componantInFram.get(i).inputWire2.tru==true) {
                    wire.this.componantInFram.get(i).input2=wire.this.value;
                    System.out.println( wire.this.componantInFram.get(i).input2);
                    wire.this.componantInFram.get(i).input2=wire.this.value;
                    System.out.println("inout2");
                    wire.this.componantInFram.get(i).cur=true;
                    wire.this.curvv=true;
                    if(wire.this.componantInFram.get(i).paa==wire.this.componantInFram.get(i).pin1) {
                        wire.this.componantInFram.get(i).yes1=true;
                        wire.this.componantInFram.get(i).getpane1(wire.this);
                    }
                    if(wire.this.componantInFram.get(i).paa==wire.this.componantInFram.get(i).pin2 ){
                        wire.this.componantInFram.get(i).yes2=true;
                        wire.this.componantInFram.get(i).getpane2(wire.this);
                    }
                    points[0].setRect(wire.this.componantInFram.get(i).paa.getX()+60,wire.this.componantInFram.get(i).paa.getY()-10,ww,hh);
                    wire.this.tr=true;
                    repaint();
                    vbn=true;
                }         
            }
            if(vbn==false){
                points[0].setRect(xx,yy,ww,hh);
                repaint();
            }
        }
        @Override
        public void mouseClicked(MouseEvent e){
        }
        @Override
        public void mouseEntered(MouseEvent e){
        }
        public void mouseDragged(MouseEvent event) {
            if (ch == false)
                return;
            points[0].setRect(event.getPoint().x,event.getPoint().y,points[0].getWidth(),points[0].getHeight());
            wire.this.curve(event.getX(), event.getY());
            repaint();
        }
    }
}

//Gates
abstract class Gates {
    private int FirstInput;
    private int SecondInput;
    private int output;

    public void setFirstInput(int FirstInput) {
        this.FirstInput = FirstInput;
    }
     
    public void setSecondInput(int SecondInput) {
        this.SecondInput = SecondInput;
    }
    
    public void setOutput(int output) {
        this.output = output;
    }

    public int getFirstInput() {
        return FirstInput;
    }

    public int getSecondInput() {
        return SecondInput;
    }
    
     public int Output() {
        return output;
    }
    
    abstract public int getOutput();
}

class AND_Gate extends Gates{
    public AND_Gate(int i1 ,int i2) {
        if(i1==1&&i2==1)
            super.setOutput(1);
        else
            super.setOutput(0);
    }

    @Override
    public int getOutput() {
        return super.Output();
    }
}

class OR_Gate extends Gates{
    public OR_Gate(int i1 ,int i2) {
        if(i1==0&&i2==0)
           super.setOutput(0);
        else
           super.setOutput(1);
    }

    @Override
    public int getOutput() {
        return super.Output();
    }
}

class NAND_Gate extends Gates {
    public NAND_Gate(int i1 ,int i2) {
        if(i1==1&&i2==1)
            super.setOutput(0);
        else
            super.setOutput(1);
    }

    @Override
    public int getOutput() {
        return super.Output();
    }
}

class NOR_Gate extends Gates{
    public NOR_Gate(int i1 ,int i2) {
        if(i1==0&&i2==0)
            super.setOutput(1);
        else
           super.setOutput(0);
    }

    @Override
    public int getOutput() {
        return super.Output();
    }
}

class XOR_Gate extends Gates {
    public XOR_Gate(int i1 ,int i2) {
        if(i1==1&&i2==1||i1==0&&i2==0)
            super.setOutput(0);
        else
            super.setOutput(1);
    }
    
    @Override
    public int getOutput() {
        return super.Output();
    }
}

class XNOR_Gate extends Gates {
    public XNOR_Gate(int i1 ,int i2) { 
        if(i1==1&&i2==1||i1==0&&i2==0)
            super.setOutput(1);
        else
            super.setOutput(0);
    }
    
    @Override
    public int getOutput() {
        return super.Output();
    }
}
//----------------------------------------------------------------------------------------------------------------------------
class Not_Gate extends Gates {
    public Not_Gate(int i1 ) {
        if(i1==1)
            super.setOutput(0);
        else if(super.getFirstInput()==0)
            super.setOutput(1);
    }
//    p
     
    @Override
    public int getOutput() {
        return super.Output();
    }
}

class Decoder_Gate  {
    int i1, i2, out1 ,out2 ,out3,out4;

    public Decoder_Gate(int i1, int i2) {
        this.i1=i1;
        this.i2=i2;
            

        if(i1==0&&i2==0) {
            out1=1;
            out2=out3=out4=0;
        } else if(i1==0&&i2==1) {
            out2=1;
            out1=out3=out4=0;
        } else if(i1==1&&i2==0) {
            out3=1;
            out1=out2=out4=0;
        } else if(i1==1&&i2==1) {
            out4=1;
            out1=out3=out3=0;
            System.out.println("out");
        }
    }
    
    public int getOutput1() {
        return out1;
    }
    
    public int getOutput2() {
        return out2;
    }
    
    public int getOutput3() {
        return out3;
    }
   
    public int getOutput4() {
        return out4;
    }
}