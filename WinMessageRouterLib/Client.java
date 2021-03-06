package WinMessageRouterLib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.util.StringTokenizer;


public class Client extends JFrame
    implements AsyncMessageReceiverListener {
  DefaultListModel onlineUsers = new DefaultListModel();
  JSplitPane jSplitPane1 = new JSplitPane();
  BorderLayout borderLayout1 = new BorderLayout();
  JTextArea jTextAreaSendMessages = new JTextArea();
  JPanel jPanel1 = new JPanel();
  JLabel jLabelServer = new JLabel();
  JTextField jTextFieldServer = new JTextField();
  JLabel jLabelUserId = new JLabel();
  JTextField jTextFieldUser = new JTextField();
  JButton jButtonConnect = new JButton();

  Session session;
  Session[] MultiSessions;
  Border border1;
  JButton jButtonDisconnect = new JButton();
  JLabel jLabeltargetUser = new JLabel();
  JTextField jTextFieldTargetUser = new JTextField();
  JPanel jPanelSendMessages = new JPanel();
  Border border2;
  Border border3;
  TitledBorder titledBorder1;
  Border border4;
  TitledBorder titledBorder2;
  JTextField jTextFieldSendSubject = new JTextField();
  Border border5;
  TitledBorder titledBorder3;
  JScrollPane jScrollPaneSendMessages = new JScrollPane();
  Border border6;
  TitledBorder titledBorder4;
  BorderLayout borderLayout2 = new BorderLayout();
  JSplitPane jSplitPane2 = new JSplitPane();
  JScrollPane jScrollPane1 = new JScrollPane();
  JScrollPane jScrollPane2 = new JScrollPane();
  JList jListOnlineUsers = new JList(onlineUsers);
  Border border7;
  TitledBorder titledBorder5;
  Border border8;
  TitledBorder titledBorder6;
  JToggleButton jToggleButtonRegister = new JToggleButton();
  JButton jButtonMultiConnect = new JButton();
  JTextArea jTextAreaDisplayMessages = new JTextArea();

  public Client() {
    try {
      jbInit();
      Flasher.instance();  //initialize the flasher instance so that it's currently running
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    border2 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    border3 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(178, 178, 178));
    titledBorder1 = new TitledBorder(border3,"Subject");
    border4 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(178, 178, 178));
    titledBorder2 = new TitledBorder(border4,"Message");
    border5 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(178, 178, 178));
    titledBorder3 = new TitledBorder(border5,"Subject");
    border6 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    titledBorder4 = new TitledBorder(border6,"Message");
    border7 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    titledBorder5 = new TitledBorder(border7,"Messages");
    border8 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(165, 163, 151));
    titledBorder6 = new TitledBorder(border8,"Online Users");
    this.getContentPane().setLayout(borderLayout1);
    jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane1.setBorder(border1);
    jSplitPane1.setLastDividerLocation(250);
    jSplitPane1.setRightComponent(jTextAreaSendMessages);
    jTextAreaSendMessages.setBorder(null);
    jTextAreaSendMessages.setToolTipText("");
    jTextAreaSendMessages.setLineWrap(true);
    jTextAreaSendMessages.addKeyListener(new Client_jTextAreaSendMessages_keyAdapter(this));
    jLabelServer.setRequestFocusEnabled(true);
    jLabelServer.setText("Server");
    jLabelUserId.setText("User Id");
    jTextFieldServer.setPreferredSize(new Dimension(75, 20));
    jTextFieldServer.setText("");
    jTextFieldUser.setPreferredSize(new Dimension(75, 20));
    jTextFieldUser.setText("");
    jButtonConnect.setText("Connect");
    jButtonConnect.addActionListener(new Client_jButtonConnect_actionAdapter(this));
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setTitle("Message Clent");
    jButtonDisconnect.setText("Disconnect");
    jButtonDisconnect.addActionListener(new Client_jButtonDisconnect_actionAdapter(this));
    jLabeltargetUser.setText("Target");
    jTextFieldTargetUser.setPreferredSize(new Dimension(75, 20));
    jTextFieldTargetUser.setText("");
    jPanelSendMessages.setBorder(border2);
    jPanelSendMessages.setPreferredSize(new Dimension(96, 107));
    jPanelSendMessages.setLayout(borderLayout2);
    jTextFieldSendSubject.setBorder(titledBorder3);
    jTextFieldSendSubject.setText("");
    jTextFieldSendSubject.addKeyListener(new Client_jTextFieldSendSubject_keyAdapter(this));
    jScrollPaneSendMessages.setBorder(titledBorder4);
    jSplitPane2.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    jScrollPane1.setBorder(titledBorder5);
    jScrollPane2.setBorder(titledBorder6);
    jToggleButtonRegister.setText("Register");
    jToggleButtonRegister.addActionListener(new Client_jToggleButtonRegister_actionAdapter(this));
    jButtonMultiConnect.setText("Multi-Connect");
    jButtonMultiConnect.addActionListener(new Client_jButtonMultiConnect_actionAdapter(this));
    jTextAreaDisplayMessages.setEditable(false);
    jTextAreaDisplayMessages.setText("");
    jListOnlineUsers.addMouseListener(new Client_jListOnlineUsers_mouseAdapter(this));
    jPanelSendMessages.add(jTextFieldSendSubject, BorderLayout.NORTH);
    jPanelSendMessages.add(jScrollPaneSendMessages, BorderLayout.CENTER);
    jSplitPane1.add(jSplitPane2, JSplitPane.TOP);
    jSplitPane2.add(jScrollPane1, JSplitPane.TOP);
    jScrollPane1.getViewport().add(jTextAreaDisplayMessages, null);
    jSplitPane2.add(jScrollPane2, JSplitPane.BOTTOM);
    jScrollPane2.getViewport().add(jListOnlineUsers, null);
    jScrollPaneSendMessages.getViewport().add(jTextAreaSendMessages, null);
    this.getContentPane().add(jSplitPane1,  BorderLayout.CENTER);
    jSplitPane1.add(jPanelSendMessages, JSplitPane.BOTTOM);
    this.getContentPane().add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jLabelServer, null);
    jPanel1.add(jTextFieldServer, null);
    jPanel1.add(jLabelUserId, null);
    jPanel1.add(jTextFieldUser, null);
    jPanel1.add(jLabeltargetUser, null);
    jPanel1.add(jTextFieldTargetUser, null);
    jPanel1.add(jButtonConnect, null);
    jPanel1.add(jButtonDisconnect, null);
    jPanel1.add(jToggleButtonRegister, null);
    jPanel1.add(jButtonMultiConnect, null);
    jSplitPane1.setDividerLocation(200);
    jSplitPane2.setDividerLocation(600);
  }

  public static void main(String[] args) {
    Client client = new Client();
    client.setSize(800,400);
    center(client);
    client.show();
  }

  public static void center (Window w) {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension windowSize = w.getSize();
      w.setLocation((screenSize.width - windowSize.width)/2, (screenSize.height - windowSize.height)/2);
  }


  void jButtonConnect_actionPerformed(ActionEvent e) {
    try {
      jTextAreaDisplayMessages.setText("");
      session = new Session();
      if (session.connect(jTextFieldServer.getText(), jTextFieldUser.getText()) ) {
        jTextAreaDisplayMessages.append("Connected\n");
        session.addListener(this);
      }
      else {
        jTextAreaDisplayMessages.append("Connection attempt failed\n");
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jButtonDisconnect_actionPerformed(ActionEvent e) {
    if (session != null && session.isConnected()) {
      session.disconnect();
    }
  }

  void jTextAreaSendMessages_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == '\n') {
      //strip off the \n
      String body = jTextAreaSendMessages.getText().substring(0, jTextAreaSendMessages.getText().length()-1);
      if (session != null && session.isConnected()) {
        session.postMessage(jTextFieldTargetUser.getText(),
                            jTextFieldSendSubject.getText(),
                            body);
      }
      jTextAreaSendMessages.setText("");
      jTextFieldSendSubject.setText("");
    }
  }

  public void receiveMsg(Message msg) {
    //System.out.println(msg.getSubject() + " : " + msg.getBody());
    String subject = msg.getSubject();
    if (subject != null && subject.equals("online")) {
      onlineUsers.addElement(msg.getBody());
    }
    else if (subject != null && subject.equals("offline")) {
      onlineUsers.removeElement(msg.getBody());
    }
    else {
      subject = subject == null?"":subject;
      String from = msg.getFrom() == null?"server":msg.getFrom();
      String body = msg.getBody()!=null?msg.getBody():"";
      jTextAreaDisplayMessages.append("FROM: " + from + " - " + subject + " : " + body);
      if (!body.endsWith("\n")) jTextAreaDisplayMessages.append("\n");
      jTextAreaDisplayMessages.setCaretPosition(jTextAreaDisplayMessages.getText().length());
    }
  }

  void jTextAreaSendSubject_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == '\n') {
      jTextAreaSendMessages.requestFocus();
    }
  }

  void jTextFieldSendSubject_keyTyped(KeyEvent e) {
    if (e.getKeyChar() == '\n') {
      jTextAreaSendMessages.requestFocus();
    }
  }

  void jToggleButtonRegister_actionPerformed(ActionEvent e) {
    if (jToggleButtonRegister.isSelected()) {
      register();
    }
    else {
      deregister();
    }
  }

  private void register() {
    if (session != null && session.isConnected()) {
      onlineUsers.clear();
      //get the current list
      Message msg = session.sendMessage("", "list", "");
      System.out.println("LIST: " + msg.getBody());
      StringTokenizer st = new StringTokenizer(msg.getBody(), "\n");
      while (st.hasMoreTokens()) {
        onlineUsers.addElement(st.nextToken());
      }
      session.postMessage("", "register", "");
    }
  }

  private void deregister() {
    onlineUsers.clear();
    if (session != null && session.isConnected()) {
     session.postMessage("", "deregister", "");
   }
  }

  void jButtonMultiConnect_actionPerformed(ActionEvent e) {
    if (jButtonMultiConnect.getText().equals("Multi-Connect")) {
      String res = JOptionPane.showInputDialog(this,
                                               "Enter number of connections",
                                               "Multi-Connect");
      if (res != null) {
        jButtonMultiConnect.setText("Multi-Disconnect");
        int count = Integer.parseInt(res);
        MultiSessions = new Session[count];
        for (int i = 0; i < count; i++) {
          MultiSessions[i] = new Session();
          MultiSessions[i].connect(this.jTextFieldServer.getText(),
                                   this.jTextFieldUser.getText() +
                                   String.valueOf(i));
        }
      }
    }
    else {
      for (int i=0; i<MultiSessions.length; i++) {
        try {
          ( (Session) MultiSessions[i]).disconnect();
        }
        catch (Exception ex) {
          System.out.println("Error disconnectiong from session");
        }
      }
      jButtonMultiConnect.setText("Multi-Connect");
    }
  }

  void jListOnlineUsers_mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
      jTextFieldTargetUser.setText((String)jListOnlineUsers.getSelectedValue());
      Flasher.instance().flashBackground(jTextFieldTargetUser, Color.yellow, 2);
      jTextAreaSendMessages.requestFocus();
    }
  }

}

class Flasher extends Thread {
  static private Flasher me;
  Object semaphore = new Object();
  Component c;
  Color flashColor;
  int flashCount;

  private Flasher(){
    start();
  }
  public static Flasher instance() {
    if (me == null) me = new Flasher();
    return me;
  }

  public static void flashBackground(Component c, Color flashColor, int flashCount) {
    me.c = c;
    me.flashColor = flashColor;
    me.flashCount = flashCount;
    synchronized(me.semaphore) {
        me.semaphore.notifyAll();
    }
  }

  public void run() {
    while (true) {
      synchronized (semaphore) {
        try {
          semaphore.wait();
        }
        catch (InterruptedException ex) {
        }
      }
      Color origColor = c.getBackground();
      for (int i=0; i<flashCount; i++) {
        c.setBackground(flashColor);
        try {
          Thread.currentThread().sleep(100);
        }
        catch (Exception ex) {}
        c.setBackground(origColor);
      }
    }
  }


}

class Client_jButtonConnect_actionAdapter implements java.awt.event.ActionListener {
  Client adaptee;

  Client_jButtonConnect_actionAdapter(Client adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonConnect_actionPerformed(e);
  }
}

class Client_jButtonDisconnect_actionAdapter implements java.awt.event.ActionListener {
  Client adaptee;

  Client_jButtonDisconnect_actionAdapter(Client adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonDisconnect_actionPerformed(e);
  }
}

class Client_jTextAreaSendMessages_keyAdapter extends java.awt.event.KeyAdapter {
  Client adaptee;

  Client_jTextAreaSendMessages_keyAdapter(Client adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.jTextAreaSendMessages_keyTyped(e);
  }
}

class Client_jTextFieldSendSubject_keyAdapter extends java.awt.event.KeyAdapter {
  Client adaptee;

  Client_jTextFieldSendSubject_keyAdapter(Client adaptee) {
    this.adaptee = adaptee;
  }
  public void keyTyped(KeyEvent e) {
    adaptee.jTextFieldSendSubject_keyTyped(e);
  }
}

class Client_jToggleButtonRegister_actionAdapter implements java.awt.event.ActionListener {
  Client adaptee;

  Client_jToggleButtonRegister_actionAdapter(Client adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jToggleButtonRegister_actionPerformed(e);
  }
}

class Client_jButtonMultiConnect_actionAdapter implements java.awt.event.ActionListener {
  Client adaptee;

  Client_jButtonMultiConnect_actionAdapter(Client adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonMultiConnect_actionPerformed(e);
  }
}

class Client_jListOnlineUsers_mouseAdapter extends java.awt.event.MouseAdapter {
  Client adaptee;

  Client_jListOnlineUsers_mouseAdapter(Client adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jListOnlineUsers_mouseClicked(e);
  }
}
