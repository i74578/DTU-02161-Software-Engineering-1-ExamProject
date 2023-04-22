package dtu.library.gui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.domain.Book;
import dtu.library.persistence.SqliteRepository;

public class MainScreen {
	
	LibraryApp libraryApp;
	SearchMediaScreen searchMediaScreen;
	AdministratorScreen administratorFunctionsScreen;

	private JFrame frame;
	private JPanel panelMenu;
	private JButton btnAdministratorFunctions;
	private JButton btnBorrowMedia;
	private JButton btnRetunMedia;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen screen = new MainScreen();
					screen.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public MainScreen() throws Exception {
		SqliteRepository repository = new SqliteRepository(true);
		libraryApp = new LibraryApp(repository,repository);
//		libraryApp.exampleData();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 404, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		panelMenu = new JPanel();
		frame.getContentPane().add(panelMenu, "name_160236068959176");
		panelMenu.setLayout(null);
		panelMenu.setBorder(BorderFactory.createTitledBorder(
                "Main Menu"));
		
		JButton btnSearchMedium = new JButton("Search Medium");
		btnSearchMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				searchMediaScreen.setVisible(true);
				
			}
		});
		btnSearchMedium.setBounds(104, 52, 193, 29);
		panelMenu.add(btnSearchMedium);
		
		btnAdministratorFunctions = new JButton("Administrator Functions");
		btnAdministratorFunctions.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				administratorFunctionsScreen.setVisible(true);
			}
		});
		btnAdministratorFunctions.setBounds(104, 93, 193, 29);
		panelMenu.add(btnAdministratorFunctions);
		
		btnBorrowMedia = new JButton("Borrow Media");
		btnBorrowMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorrowMedia.setBounds(104, 134, 193, 29);
		panelMenu.add(btnBorrowMedia);
		
		btnRetunMedia = new JButton("Retun Media");
		btnRetunMedia.setBounds(104, 175, 193, 29);
		panelMenu.add(btnRetunMedia);
		searchMediaScreen = new SearchMediaScreen(libraryApp, this);
		administratorFunctionsScreen = new AdministratorScreen(libraryApp, this);
		
	}
	
	public void setVisible(boolean aFlag) {
		panelMenu.setVisible(aFlag);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void addPanel(JPanel panel) {
		frame.getContentPane().add(panel);
	}


}
