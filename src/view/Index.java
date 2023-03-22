package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.ItemDatabase;
import model.Item;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Index extends JFrame {

	private JPanel contentPane;
	private JTextField numbert;
	private File file;
	JTextField fileName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 450, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Browse");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				int response = chooser.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					file = new File(chooser.getSelectedFile().getAbsolutePath());
					fileName.setText(file.getName());
				}
			}
		});

		btnNewButton.setBounds(127, 283, 171, 56);
		contentPane.add(btnNewButton);

		numbert = new JTextField();
		numbert.setBounds(34, 72, 201, 48);
		contentPane.add(numbert);
		numbert.setColumns(10);

		JLabel lblNewLabel = new JLabel("عدد النسخ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(260, 74, 164, 37);
		contentPane.add(lblNewLabel);

		JButton create = new JButton("انشاء");
		create.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					long number = Long.parseLong(numbert.getText());
					if (file == null)
						JOptionPane.showMessageDialog(null, "ادخل الملف");
					else {
						System.out.println(number);
						Item item = new Item(0, file.getName(), number, file.getAbsolutePath(),
								String.valueOf(LocalDate.now()));
						new ItemDatabase().addItem(item);
						JOptionPane.showMessageDialog(null, "تمت المهمة بنجااح");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "يجب عليك ادخال رقم بدون اي حروف");
				}
			}
		});
		create.setFont(new Font("Tahoma", Font.BOLD, 20));
		create.setBounds(127, 366, 171, 56);
		contentPane.add(create);

		fileName = new JTextField("");
		fileName.setForeground(Color.RED);
		fileName.setFont(new Font("Tahoma", Font.BOLD, 20));
		fileName.setBounds(10, 141, 414, 131);
		contentPane.add(fileName);

		JButton create_1 = new JButton("البيانات");
		create_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Render().setVisible(true);
			}
		});
		create_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		create_1.setBounds(10, 11, 171, 37);
		contentPane.add(create_1);
	}
}
