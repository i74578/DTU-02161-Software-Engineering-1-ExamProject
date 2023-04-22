package dtu.library.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dtu.library.app.LibraryApp;
import dtu.library.domain.Medium;
import javax.swing.JPasswordField;

public class AdministratorScreen implements Observer {

	private MainScreen parentWindow;
	private LibraryApp libraryApp;
	private JPanel panelAdministratorFunctions;
	private JButton btnBack;
	private JButton btnAddMedium;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JLabel lblEnterPasswordStatus = new JLabel("");
	private JLabel lblLoginStatus;
	private JButton btnLogout;
	private JButton btnPayFine;
	private JButton btnRegisterUser;
	private JButton btnUnregisterUser;

	public AdministratorScreen(LibraryApp libraryApp, MainScreen parentWindow) {
		this.libraryApp = libraryApp;
		this.parentWindow = parentWindow;
		initialize();
	}

	public void initialize() {
		panelAdministratorFunctions = new JPanel();
		parentWindow.addPanel(panelAdministratorFunctions);
		panelAdministratorFunctions.setLayout(null);
		panelAdministratorFunctions.setBorder(BorderFactory.createTitledBorder("Administrator Functions"));

		btnAddMedium = new JButton("Add Medium");
		btnAddMedium.setEnabled(false);
		btnAddMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddMedium.setBounds(132, 171, 141, 29);
		panelAdministratorFunctions.add(btnAddMedium);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				libraryApp.adminLogout();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 74, 29);
		panelAdministratorFunctions.add(btnBack);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(166, 33, 74, 16);
		panelAdministratorFunctions.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean loginOk = libraryApp.adminLogin(passwordField.getText());
				passwordField.setText("");
				if (loginOk) {
					lblEnterPasswordStatus.setText("");
				} else {
					lblEnterPasswordStatus.setText("login failed");
				}
			}
		});
		passwordField.setBounds(252, 28, 130, 26);
		panelAdministratorFunctions.add(passwordField);
		
		lblLoginStatus = new JLabel("");
		lblLoginStatus.setBounds(145, 102, 112, 16);
		panelAdministratorFunctions.add(lblLoginStatus);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				libraryApp.adminLogout();
			}
		});
		btnLogout.setEnabled(false);
		btnLogout.setBounds(132, 130, 141, 29);
		panelAdministratorFunctions.add(btnLogout);
		
		btnPayFine = new JButton("Pay Fine");
		btnPayFine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPayFine.setEnabled(false);
		btnPayFine.setBounds(132, 294, 141, 29);
		panelAdministratorFunctions.add(btnPayFine);
		
		btnRegisterUser = new JButton("Register User");
		btnRegisterUser.setEnabled(false);
		btnRegisterUser.setBounds(132, 212, 141, 29);
		panelAdministratorFunctions.add(btnRegisterUser);
		
		btnUnregisterUser = new JButton("Unregister User");
		btnUnregisterUser.setEnabled(false);
		btnUnregisterUser.setBounds(132, 253, 141, 29);
		panelAdministratorFunctions.add(btnUnregisterUser);
		
		lblEnterPasswordStatus.setBounds(145, 74, 112, 16);
		panelAdministratorFunctions.add(lblEnterPasswordStatus);

		disableButtons();
		displayLoginStatus();
		libraryApp.addObserver(this);
	}

	private void enableButtons() {
		setEnableButtons(true);
	}

	private void disableButtons() {
		setEnableButtons(false);
	}

	private void setEnableButtons(boolean enabled) {
		btnAddMedium.setEnabled(enabled);
		btnLogout.setEnabled(enabled);
		btnRegisterUser.setEnabled(enabled);
		btnUnregisterUser.setEnabled(enabled);
		btnPayFine.setEnabled(enabled);
	}

	public void setVisible(boolean visible) {
		if (!visible) {
			disableButtons();
		}
		panelAdministratorFunctions.setVisible(visible);
	}

	@Override
	public void update(Observable o, Object arg) {
		boolean loggedIn = libraryApp.adminLoggedIn();
		if (loggedIn) {
			enableButtons();
			passwordField.setEnabled(false);
			
		} else {
			disableButtons();
			passwordField.setEnabled(true);
		}
		displayLoginStatus();
	}

	protected void displayLoginStatus() {
		boolean loggedIn = libraryApp.adminLoggedIn();
		if (loggedIn) {
			lblLoginStatus.setText("Admin logged in");
		} else {
			lblLoginStatus.setText("Admin logged off");
		}
	}
}
