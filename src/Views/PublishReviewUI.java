package Views;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import graphics.GUIimage;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class PublishReviewUI extends JPanel{
	public JButton btnBack ;
	public PublishReviewUI() {
		this.setBounds(0, 0, 677, 562);
		this.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 126, 677, 12);
		add(separator);
		
		JLabel lblPublishReview = new JLabel("Publish Review");
		lblPublishReview.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblPublishReview.setBounds(252, 149, 180, 30);
		add(lblPublishReview);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(131, 214, 163, 20);
		add(comboBox);
		
		JLabel lblBookName = new JLabel("Book name :");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookName.setBounds(41, 212, 80, 20);
		add(lblBookName);
		
		JLabel lblRate = new JLabel("Rate :");
		lblRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRate.setBounds(41, 247, 80, 20);
		add(lblRate);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox_1.setBounds(131, 249, 42, 20);
		add(comboBox_1);
		
		JLabel lblReview = new JLabel("Review :");
		lblReview.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblReview.setBounds(41, 278, 80, 20);
		add(lblReview);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 309, 603, 172);
		add(scrollPane);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(41, 492, 89, 30);
		add(btnBack);
		
		JButton btnNewButton = new JButton("Publish");
		btnNewButton.setBounds(555, 492, 89, 30);
		add(btnNewButton);
		
		JLabel lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 671, 533);
		lblBackground.setIcon(new GUIimage("Background",lblBackground.getWidth(),lblBackground.getHeight()).image);
		add(lblBackground);
	}
}