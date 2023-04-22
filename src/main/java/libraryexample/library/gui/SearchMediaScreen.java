package dtu.library.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class SearchMediaScreen {

	private MainScreen parentWindow;
	private LibraryApp libraryApp;
	private JPanel panelSearchMedia;
	private JTextField searchField;
	private JList<Medium> listSearchResult;
	private DefaultListModel<Medium> searchResults;
	private JLabel lblSearchResultDetail;
	private JButton btnBack;
	
	public SearchMediaScreen(LibraryApp libraryApp, MainScreen parentWindow) {
		this.libraryApp = libraryApp;
		this.parentWindow = parentWindow;
		initialize();
	}
	
	public void initialize() {
		panelSearchMedia = new JPanel();
		parentWindow.addPanel(panelSearchMedia);
		panelSearchMedia.setLayout(null);
		panelSearchMedia.setBorder(BorderFactory.createTitledBorder(
                "Search Media"));
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMedia();
			}
		});
		searchField.setBounds(138, 28, 130, 26);
		panelSearchMedia.add(searchField);
		searchField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchMedia();
			}
		});
		btnSearch.setBounds(148, 68, 117, 29);
		panelSearchMedia.add(btnSearch);
		btnSearch.getRootPane().setDefaultButton(btnSearch);
		
		searchResults = new DefaultListModel<>();
		listSearchResult = new JList<Medium>(searchResults);
		listSearchResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSearchResult.setSelectedIndex(0);
		listSearchResult.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
		        if (e.getValueIsAdjusting() == false) {

		            if (listSearchResult.getSelectedIndex() == -1) {
		            	lblSearchResultDetail.setText("");

		            } else {
		            	lblSearchResultDetail.setText(listSearchResult.getSelectedValue().printDetail());
		            }
		        }
			}
		});
		listSearchResult.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(listSearchResult);

        listScrollPane.setBounds(21, 109, 361, 149);
		panelSearchMedia.add(listScrollPane);
		
		JPanel panelSearchResult = new JPanel();
		panelSearchResult.setBounds(21, 270, 361, 175);
		panelSearchMedia.add(panelSearchResult);
		panelSearchResult.setBorder(BorderFactory.createTitledBorder(
                "Detail"));
		panelSearchResult.setLayout(null);
		
		lblSearchResultDetail = new JLabel("");
		lblSearchResultDetail.setVerticalAlignment(SwingConstants.TOP);
		lblSearchResultDetail.setHorizontalAlignment(SwingConstants.LEFT);
		lblSearchResultDetail.setBounds(23, 19, 318, 137);
		panelSearchResult.add(lblSearchResultDetail);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				clear();
				parentWindow.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 59, 29);
		panelSearchMedia.add(btnBack);


	}
	protected void searchMedia() {
		searchResults.clear();
		libraryApp.search(searchField.getText())
		.forEach((m) -> {searchResults.addElement(m);});
	}

	public void setVisible(boolean aFlag) {
		panelSearchMedia.setVisible(aFlag);
	}

	public void clear() {
		searchField.setText("");
		searchResults.clear();
	}
}
