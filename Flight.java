package JAVA;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Login extends Registration implements ActionListener {
	JFrame f, f1;
	JPanel p, p1;
	JLabel wel, l2, l3;
	JTextField t1;
	JPasswordField pass;
	JButton b1, b2;
	Connection conn;
	PreparedStatement ps;

	Login() {//constructor 
		f = new JFrame("Login Page");
		p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		wel = new JLabel("Username : ");
		t1 = new JTextField(20);
		l2 = new JLabel("Password : ");
		pass = new JPasswordField(17);
		//cb = new JCheckBox("Show");
		// ImageIcon ic = new ImageIcon("D:\\Projects\\websites\\java codes\\img.png");
		b1 = new JButton("New Account");
		b2 = new JButton("Submit");
		l3 = new JLabel("  ");

		p.add(wel);
		p.add(t1);
		p.add(l2);
		p.add(pass);
		//p.add(cb);
		p.add(b1);
		p.add(b2);
		p.add(l3);
		f.add(p);
		//cb.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		f.setVisible(true);
		regf.setVisible(false);
		f.setSize(350, 400);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent a) {
		String userName = t1.getText();
		String password = new String(pass.getPassword());
		if(a.getSource().equals(b1)) {//if user click on "new account" button then registration page will open 
			 new Registration();
		}
		if(a.getSource().equals(b2)) {//if user click on "submit" button then book ticket page will open

		try {//this is for database connection
			PreparedStatement stmt;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loggin", "root", "root");

			stmt = con.prepareStatement("select username,password from user where username=? and password=?");
			stmt.setString(1, userName);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			if (t1.getText().equals("")) { // if text field are blank then  show a message
				JOptionPane.showMessageDialog(null, "Enter the Username ");
			} else if (pass.getPassword().equals("")) {// if text field are blank then  show a message
				JOptionPane.showMessageDialog(null, "Enter the Password ");
			}

			else if (rs.next()) {

				JOptionPane.showMessageDialog(null, "Login Successfull");

				new BookTickets();
				f.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "Username and Password is Invalid ");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}
}

// registration page.....

class Registration extends JFrame implements ActionListener {
	JFrame regf;
	JPanel regp;
	JLabel fn, ln, un, pasl, eml, pnl, genl, addl, det;
	public JTextField t1, t2, t3, emt, pnt;
	JTextArea addt;
	public JPasswordField regpass;
	JRadioButton m, fm, ot;
	JCheckBox regcb;
	ButtonGroup bg;
	JButton creacc;
	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ps;

	Registration() {
		regf = new JFrame("Registration Page");
		regp = new JPanel(new FlowLayout(FlowLayout.CENTER));

		fn = new JLabel("First Name : ");
		t1 = new JTextField(20);

		ln = new JLabel("Last Name : ");
		t2 = new JTextField(20);

		un = new JLabel("Username : ");
		t3 = new JTextField(20);

		pasl = new JLabel("Password : ");
		regpass = new JPasswordField(17);
		regcb = new JCheckBox("Show");

		pnl = new JLabel("Passport No.: ");
		pnt = new JTextField(20);

		eml = new JLabel("Enter Email : ");
		emt = new JTextField(20);

		genl = new JLabel("Select Gender: ");
		m = new JRadioButton("Male");
		fm = new JRadioButton("Female");
		ot = new JRadioButton("Other");
		bg = new ButtonGroup();
		bg.add(m);
		bg.add(fm);
		bg.add(ot);

		addl = new JLabel("Address : ");
		addt = new JTextArea(5, 25);

		creacc = new JButton("Create Account");

		det = new JLabel("     ");

		regp.add(fn);
		regp.add(t1);

		regp.add(ln);
		regp.add(t2);

		regp.add(un);
		regp.add(t3);

		regp.add(pasl);
		regp.add(regpass);
		regp.add(regcb);

		regp.add(pnl);
		regp.add(pnt);

		regp.add(eml);
		regp.add(emt);

		regp.add(genl);
		regp.add(m);
		regp.add(fm);
		regp.add(ot);

		regp.add(addl);
		regp.add(addt);

		regp.add(creacc);

		regp.add(det);

		regcb.addActionListener(this);
		creacc.addActionListener(this);
		regf.add(regp);
		regf.setVisible(true);
		regf.setSize(355, 400);
		regf.setResizable(false);
		regf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent a) {
		String first_name = t1.getText();// t1
		String last_name = t2.getText();// t2
		String username = t3.getText();// username
		String password = new String(regpass.getPassword());
		String passport_no = pnt.getText();
		String email_id = emt.getText();
		String address = addt.getText();

		if (regcb.isSelected()) {
			regpass.setEchoChar((char) 0);
		} else {
			regpass.setEchoChar('*');
		}

		if (a.getSource() == creacc) {
			det.setText("First Name: " + t1.getText() + " Last Name: " + t2.getText() + " Username: " + t3.getText()
					+ " Password: " + regpass.getPassword() + " Passport No.: " + pnt.getText() + " Email: "
					+ emt.getText() + " Address: " + addt.getText());
			System.out.println("First Name: " + t1.getText() + "\nLast Name: " + t2.getText() + "\nUsername: "
					+ t3.getText() + "\nPassword: " + regpass.getText() + "\nPassport No.: " + pnt.getText()
					+ "\nEmail: " + emt.getText() + "\nAddress: " + addt.getText());
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loggin", "root", "root");

				ps = con.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
				ps.setString(1, first_name);
				ps.setString(2, last_name);
				ps.setString(3, username);
				ps.setString(4, password);
				ps.setString(5, passport_no);
				ps.setString(6, email_id);
				ps.setString(7, address);
				int rst = ps.executeUpdate();
				System.out.println(rst + " Record inserted.");

				ps.close();

				con.close();
			} catch (Exception e) {
				System.out.println(e);

			}

			new Login();
			regf.setVisible(false);
			// p.setVisible(true);
		}
	}
}

//book tickets...

class BookTickets extends JFrame implements ActionListener {
	JFrame btf;
	JPanel btp, btip, stip;
	JLabel l, wel, tolab, fromlab, deplab, classlab, travlab, adultlab, inflab, childlab, setfli;
	JMenuBar mb;
	JMenu book, dis, show;
	JMenuItem bookhere, showticket;
	JTextField totxt, fromtxt, deptxt, adulttxt, inftxt, childtxt;
	JComboBox cb;
	JRadioButton indi, spice, aisa;
	ButtonGroup bg;
	JTable t;
	JButton cnf;
	JScrollPane jsp;
	JSpinner spinner;
	SpinnerModel model;
	PreparedStatement ps;
	Connection con;

	BookTickets() {
		btf = new JFrame("MY AIRLINES");
		btp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mb = new JMenuBar();
		book = new JMenu("Book Tickets");
		//dis = new JMenu("Display Tickets");
		show = new JMenu("Show Flights");
		bookhere = new JMenuItem("Book Here");
		showticket = new JMenuItem("Show");
		btip = new JPanel(new FlowLayout(FlowLayout.LEFT));
		wel = new JLabel("                        WELCOME TO MY AIRLINES                              ");
		l = new JLabel("HELLOO");
		fromlab = new JLabel("From:   ");
		fromtxt = new JTextField(13);
		tolab = new JLabel("To:   ");
		totxt = new JTextField(13);
		deplab = new JLabel("          Departure Date:   ");
		deptxt = new JTextField(15);
		classlab = new JLabel("Select class:   ");
		String s1[] = { "Economy", "Business", "Premium" };
		cb = new JComboBox(s1);
		travlab = new JLabel("Type of Travellers:                                                        ");
		adultlab = new JLabel("         Adults(12y+): ");
		adulttxt = new JTextField(3);
		childlab = new JLabel("          Child(2y-12y):");
		childtxt = new JTextField(3);
		inflab = new JLabel("Infants(below 2y):");
		inftxt = new JTextField(3);
		setfli = new JLabel("Select The Flight:                      ");
		indi = new JRadioButton("Indigo.....8:00am.....                      ");
		spice = new JRadioButton("Spicejet.....12:00pm.....                  ");
		aisa = new JRadioButton("Air Aisa.........3:00pm.....                                     ");
		bg = new ButtonGroup();
		bg.add(indi);
		bg.add(spice);
		bg.add(aisa);
		cnf = new JButton("Confirm Ticket");
		// model = new SpinnerNumberModel(1, 1, 20, 1);
		// spinner = new JSpinner(model);
		stip = new JPanel(new FlowLayout(FlowLayout.CENTER));
		String data[][] = { { "Air India", "9:00am" }, { "Indigo", "11:00am" }, { "Air Aisa", "3:00pm" },
				{ "Go First", "6:00pm" }, { "Emirates", "10:00pm" } };
		String col[] = { "Flight Name", "Timing" };
		t = new JTable(data, col);
		jsp = new JScrollPane(t);
	

		// stip.add(l);
		stip.add(jsp);
		btip.add(fromlab);
		btip.add(fromtxt);
		btip.add(tolab);
		btip.add(totxt);
		btip.add(deplab);
		btip.add(deptxt);
		btip.add(travlab);
		btip.add(adultlab);
		btip.add(adulttxt);
		btip.add(childlab);
		btip.add(childtxt);
		btip.add(inflab);
		btip.add(inftxt);
		btip.add(classlab);
		btip.add(cb);
		btip.add(setfli);
		btip.add(indi);
		btip.add(spice);
		btip.add(aisa);
		btip.add(cnf);
		// btip.add(spinner);
		mb.add(book);
//		mb.add(dis);
		mb.add(show);
		book.add(bookhere);
		show.add(showticket);
		btf.setJMenuBar(mb);
		btp.add(wel);
		btf.add(btp);
		bookhere.addActionListener(this);
		showticket.addActionListener(this);
		cnf.addActionListener(this);
		btip.setVisible(false);
		stip.setVisible(false);
		btp.setVisible(true);
		btf.setVisible(true);
		btf.setResizable(false);
		btf.setSize(380, 400);
	}

	public void actionPerformed(ActionEvent a) {
		if (a.getSource() == bookhere) {
			btp.setVisible(false);
			stip.setVisible(false);
			btip.setVisible(true);
			btf.setSize(380, 400);
			btf.add(btip);
		} else if (a.getSource() == showticket) {
			btp.setVisible(false);
			btip.setVisible(false);
			stip.setVisible(true);
			btf.setSize(600, 300);
			btf.add(stip);
		}
		else if(a.getSource().equals(cnf)) {
			String form1=fromtxt.getText();
			String to1=totxt.getText();
			String date1=deptxt.getText();
			String airlines=null;
			String adult=adulttxt.getText();
			String child=childtxt.getText();
			String infrant=inftxt.getText();
			if(indi.isSelected()) {
				airlines="Indigo.....8:00am.....";
			}
			else if(spice.isSelected()) {
				airlines="Spicejet.....12:00pm.....";
			}
			else if(aisa.isSelected()) {
				airlines="Air Aisa.........3:00pm.....";
			}
			JOptionPane.showMessageDialog(null, "succesfully booked");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loggin", "root", "root");

				ps = con.prepareStatement("insert into detail3 values(?,?,?,?,?,?,?)");
				ps.setString(1, form1);
				ps.setString(2, to1);
				ps.setString(3, date1);
				ps.setString(4, airlines);
				ps.setString(5, adult);
				ps.setString(6, child);
				ps.setString(7, infrant);
				int rst = ps.executeUpdate();
				System.out.println(rst + " Record inserted.");

				ps.close();

				con.close();
			} catch (Exception e) {
				System.out.println(e);

			}
	
		}
	}
}


public class Flight {

	public static void main(String[] args) {
		new Login();
		
	}
}