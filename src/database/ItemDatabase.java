package database;

import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Item;

public class ItemDatabase extends DatabaseConnection {

	public void addItem(Item item) {
		try {
			connection = createConnection();
			sql = " INSERT INTO file (name , number , path , date ) VALUES (?,?,?,?)";
			s = connection.prepareStatement(sql);
			s.setString(1, item.getName());
			System.out.println("From DB File " + item.getNumber());
			s.setLong(2, item.getNumber());
			s.setString(3, item.getPath());
			s.setString(4, String.valueOf(LocalDate.now()));
			s.execute();
			s.close();
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Item getItemById(long id) {
		try {
			connection = createConnection();
			sql = "SELECT * FROM FILE WHERE id=?";
			s = connection.prepareStatement(sql);
			s.setLong(1, id);
			result = s.executeQuery();
			Item item = null;
			if (result.next())
				item = new Item(result.getInt(1), result.getString(2), result.getLong(3), result.getString(4),
						result.getString(5));
			connection.close();
			s.close();
			result.close();
			return item;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}

	}

	public ArrayList<Item> getAllItems() throws SQLException {
		connection = createConnection();
		sql = "SELECT * FROM file ";
		s = connection.prepareStatement(sql);
		result = s.executeQuery();
		ArrayList<Item> items = new ArrayList<>();
		while (result.next()) {
			items.add(new Item(result.getInt(1), result.getString(2), result.getLong(3), result.getString(4),
					result.getString(6)));
		}
		connection.close();
		s.close();
		result.close();
		return items;
	}

	public void deleteItem(int id) {
		try {
			connection = createConnection();
			sql = "DELETE FROM file WHERE id =?";
			s = connection.prepareStatement(sql);
			s.setInt(1, id);
			s.execute();
			connection.close();
			s.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public ArrayList<Item> getAllItems(String name) {
		try {
			connection = createConnection();
			name = "'%" + name + "%'";
			sql = "SELECT * FROM FILE WHERE name LIKE " + name;
			s = connection.prepareStatement(sql);
			result = s.executeQuery();
			ArrayList<Item> items = new ArrayList<>();
			while (result.next()) {
				items.add(new Item(result.getInt(1), result.getString(2), result.getLong(3), result.getString(4),
						result.getString(6)));
			}
			connection.close();
			s.close();
			result.close();
			return items;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			try {
				connection.close();
				s.close();
				result.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			return null;
		}

	}
}
