package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Service.MusicService;
import database.ItemDatabase;
import model.Item;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Render extends JFrame {

	private JPanel contentPane;
	private JTextField idt;
	private JTextField numbert;
	private String directory;
	private int number;
	private String[] header = { "ID", "الاسم", "عدد النسخ", "مكان الملف", "التاريخ" };
	private String[][] body;
	private ArrayList<Item> items;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField namet;
	private JTextField fromt;
	private JTextField tot;
	private JTextField searcht;
	private JButton render_1;
	private JButton delete;
	private JButton search_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Render frame = new Render();
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
	public Render() {
		System.out.println(Long.MAX_VALUE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(5, 50, 1300, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(267, 40, 1007, 343);
		contentPane.add(scrollPane);
		try {
			items = new ItemDatabase().getAllItems();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		setTable();

		idt = new JTextField();
		idt.setFont(new Font("Dialog", Font.BOLD, 14));
		idt.setBounds(5, 135, 205, 20);
		contentPane.add(idt);
		idt.setColumns(10);

		JLabel id = new JLabel("ID");
		id.setFont(new Font("Tahoma", Font.BOLD, 20));
		id.setBounds(31, 99, 114, 25);
		contentPane.add(id);

		JButton render = new JButton("استخراج");
		render.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (idt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "قم ب ادخال ال ID او اختر الملف من الجدول");
				} else {
					JOptionPane.showMessageDialog(null, "يتم الاستخراج الان");
					print(Long.parseLong(numbert.getText()));
					JOptionPane.showMessageDialog(null, "تم الاستخراج بنجاح");
				}

			}
		});
		render.setFont(new Font("Tahoma", Font.BOLD, 20));
		render.setBounds(18, 301, 171, 56);
		contentPane.add(render);

		numbert = new JTextField();
		numbert.setFont(new Font("Dialog", Font.BOLD, 14));
		numbert.setText("1");
		numbert.setBounds(5, 270, 205, 20);
		contentPane.add(numbert);
		numbert.setColumns(10);

		JLabel number = new JLabel("رقم النسخة");
		number.setFont(new Font("Tahoma", Font.BOLD, 20));
		number.setBounds(41, 234, 136, 25);
		contentPane.add(number);

		JLabel lblId = new JLabel("الاسم");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblId.setBounds(31, 167, 114, 25);
		contentPane.add(lblId);

		namet = new JTextField();
		namet.setFont(new Font("Dialog", Font.BOLD, 14));
		namet.setColumns(10);
		namet.setBounds(5, 203, 205, 20);
		contentPane.add(namet);

		JLabel id_1 = new JLabel("من");
		id_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		id_1.setBounds(138, 387, 114, 25);
		contentPane.add(id_1);

		JLabel id_2 = new JLabel("الي");
		id_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		id_2.setBounds(138, 430, 114, 25);
		contentPane.add(id_2);

		fromt = new JTextField();
		fromt.setText("1");
		fromt.setFont(new Font("Dialog", Font.BOLD, 14));
		fromt.setColumns(10);
		fromt.setBounds(5, 391, 114, 20);
		contentPane.add(fromt);

		tot = new JTextField();
		tot.setText("1");
		tot.setFont(new Font("Dialog", Font.BOLD, 14));
		tot.setColumns(10);
		tot.setBounds(5, 436, 114, 20);
		contentPane.add(tot);

		JButton render2 = new JButton("استخراج");
		render2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "يتم الاستخراج الان");
				long from = Long.parseLong(fromt.getText());
				long to = Long.parseLong(tot.getText());
				for (long i = from; i <= to; i++) {
					print(i);
				}
			}
		});
		render2.setFont(new Font("Tahoma", Font.BOLD, 20));
		render2.setBounds(18, 467, 171, 56);
		contentPane.add(render2);

		JButton search = new JButton("بحث");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAllItems();
				setTable();

			}
		});
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					items = new ItemDatabase().getAllItems(searcht.getText());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				setTable();
			}
		});
		search.setFont(new Font("Tahoma", Font.BOLD, 14));
		search.setBounds(1033, 8, 89, 23);
		contentPane.add(search);

		searcht = new JTextField();
		searcht.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					search.doClick();
				}
			}
		});
		searcht.setFont(new Font("Tahoma", Font.BOLD, 12));
		searcht.setBounds(848, 10, 160, 20);
		contentPane.add(searcht);
		searcht.setColumns(10);

		render_1 = new JButton("جلب الملفات");
		render_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Index().setVisible(true);
			}
		});
		render_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		render_1.setBounds(5, 8, 171, 56);
		contentPane.add(render_1);

		delete = new JButton("حذف");
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (idt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "أختر الملف.");
				} else {
					new ItemDatabase().deleteItem(Integer.parseInt(idt.getText()));
					JOptionPane.showMessageDialog(null, "تم الحذف بنجاح");
					getAllItems();
					setTable();
				}
			}
		});
		delete.setFont(new Font("Tahoma", Font.BOLD, 20));
		delete.setBounds(620, 488, 171, 56);
		contentPane.add(delete);

		search_1 = new JButton("اعاده تحميل");
		search_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Render().setVisible(true);
			}
		});
		search_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		search_1.setBounds(1151, 6, 123, 23);
		contentPane.add(search_1);

		JButton Play = new JButton("Play");
		Play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (idt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "قم ب ادخال ال ID او اختر الملف من الجدول");
				} else {
					long id = Long.parseLong(idt.getText());
					Item item = new ItemDatabase().getItemById(id);
//					MusicService.startMusic(item.getPath());
					MusicService.startMusic2(item.getPath());
				}
			}
		});
		Play.setFont(new Font("Tahoma", Font.BOLD, 20));
		Play.setBounds(620, 394, 171, 56);
		contentPane.add(Play);
	}

	private void setTable() {
		try {

			int len = items.size();
			body = new String[len][5];
			for (int i = 0; i < len; i++) {
				body[i][0] = String.valueOf(items.get(i).getId());
				body[i][1] = items.get(i).getName();
				body[i][2] = String.valueOf(items.get(i).getNumber());
				body[i][3] = items.get(i).getPath();
				body[i][4] = items.get(i).getDate();
			}
			table = new JTable(body, header);
			table.setFont(new Font("Tahoma", Font.BOLD, 14));
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = table.getSelectedRow();
					idt.setText(String.valueOf(items.get(index).getId()));
					namet.setText(items.get(index).getName());
				}

			});
			scrollPane.setViewportView(table);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void print(long index) {
		int id = Integer.parseInt(idt.getText());
		Item item = new ItemDatabase().getItemById(id);

		if (item == null) {
			JOptionPane.showMessageDialog(null, "ال ID غير موجود");
		} else {

			File srcFile = new File(item.getPath());
			String directory = srcFile.getName();
			String[] split = directory.split("\\.");
			File folder = new File(split[0]);
			folder.mkdirs();
			try {
				Thread.sleep(500);
//				File file = new File("\\" + split[0] + "." + split[1]);
				File file = new File(
						folder.getAbsolutePath() + "\\" + split[0] + String.valueOf(index) + "." + split[1]);

				Files.copy(srcFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (InterruptedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}

	}

	private void getAllItems() {
		try {
			items = new ItemDatabase().getAllItems(searcht.getText());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
}
