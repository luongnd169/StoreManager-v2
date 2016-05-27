package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import controller.ItemTableModel;
import dao.ItemDAO;
import data.Data;
import lib.Convert;
import model.Customer;
import model.Item;
import model.ItemDetail;

public class AddItem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtTenSanPham;
	private JTextField txtGiaNhap;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxNhaCungCap;
	private JTextField txtImei;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxLoai;
	private JTextField txtSoLuong;
	private JButton btnLuu;
	private JButton btnHuy;

	public JTextField getTxtTenSanPham() {
		return txtTenSanPham;
	}

	public void setTxtTenSanPham(JTextField txtTenSanPham) {
		this.txtTenSanPham = txtTenSanPham;
	}

	public JTextField getTxtGiaNhap() {
		return txtGiaNhap;
	}

	public void setTxtGiaNhap(JTextField txtGiaNhap) {
		this.txtGiaNhap = txtGiaNhap;
	}

	public JTextField getTxtSoLuong() {
		return txtSoLuong;
	}

	public void setTxtSoLuong(JTextField txtSoLuong) {
		this.txtSoLuong = txtSoLuong;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxNhaCungCap() {
		return comboBoxNhaCungCap;
	}

	@SuppressWarnings("rawtypes")
	public void setComboBoxNhaCungCap(JComboBox comboBoxNhaCungCap) {
		this.comboBoxNhaCungCap = comboBoxNhaCungCap;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxLoai() {
		return comboBoxLoai;
	}

	@SuppressWarnings("rawtypes")
	public void setComboBoxLoai(JComboBox comboBoxLoai) {
		this.comboBoxLoai = comboBoxLoai;
	}

	public JTextField getTxtImei() {
		return txtImei;
	}

	public void setTxtImei(JTextField txtImei) {
		this.txtImei = txtImei;
	}

	public JButton getBtnLuu() {
		return btnLuu;
	}

	public void setBtnLuu(JButton btnLuu) {
		this.btnLuu = btnLuu;
	}

	public JButton getBtnHuy() {
		return btnHuy;
	}

	public void setBtnHuy(JButton btnHuy) {
		this.btnHuy = btnHuy;
	}

	public AddItem(List<Item> list) {
		initialize(list);
		tranfer();
		disposeFrame();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize(final List<Item> list) {

		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(null);
		setVisible(true);

		JLabel lblTenSanPham = new JLabel("Tên sản phẩm");
		lblTenSanPham.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTenSanPham.setBounds(10, 10, 80, 18);
		getContentPane().add(lblTenSanPham);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setBounds(120, 10, 210, 20);
		getContentPane().add(txtTenSanPham);
		txtTenSanPham.setColumns(10);

		JLabel lblLoai = new JLabel("Loại");
		lblLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoai.setBounds(10, 51, 80, 18);
		getContentPane().add(lblLoai);

		comboBoxLoai = new JComboBox();
		comboBoxLoai.setBounds(120, 51, 90, 20);
		List<String> types = Main.getTypes();
		for (String s : types) {
			comboBoxLoai.addItem(s);
		}
		getContentPane().add(comboBoxLoai);

		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSoLuong.setBounds(220, 51, 50, 20);
		getContentPane().add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(280, 51, 50, 20);
		txtSoLuong.setText("1");
		getContentPane().add(txtSoLuong);
		txtSoLuong.setColumns(10);

		JLabel lblGiaNhap = new JLabel("Giá nhập");
		lblGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGiaNhap.setBounds(10, 96, 80, 18);
		getContentPane().add(lblGiaNhap);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setBounds(120, 96, 210, 20);
		getContentPane().add(txtGiaNhap);

		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp");
		lblNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNhaCungCap.setBounds(10, 140, 80, 18);
		getContentPane().add(lblNhaCungCap);

		comboBoxNhaCungCap = new JComboBox();
		comboBoxNhaCungCap.setBounds(120, 140, 210, 20);
		List<Customer> listProvider = Data.getCustomer(true);
		for (Customer c : listProvider) {
			comboBoxNhaCungCap.addItem(c.getName());
		}
		getContentPane().add(comboBoxNhaCungCap);

		JLabel lblImei = new JLabel("Imei");
		lblImei.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblImei.setBounds(10, 183, 80, 18);
		getContentPane().add(lblImei);

		setTxtImei(new JTextField());
		getTxtImei().setColumns(10);
		getTxtImei().setBounds(120, 183, 210, 20);
		getContentPane().add(getTxtImei());

		btnLuu = new JButton("Lưu");
		btnLuu.setBounds(122, 227, 89, 23);
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveItem();
				Main.tableTonKho.setModel(new ItemTableModel(list));
				int total = 0;
				for(Item i : list){
					total += Integer.parseInt(i.getPrice()) * i.getQuantity();
				}
				Main.txtTongTienKho.setText(Convert.numberToString(String.valueOf(total)));
			}
		});
		getContentPane().add(btnLuu);

		btnHuy = new JButton("Hủy");
		btnHuy.setBounds(241, 227, 89, 23);
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnHuy);
	}

	private void tranfer() {
		txtTenSanPham.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtTenSanPham.transferFocus();
				}
			}
		});

		comboBoxLoai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBoxLoai.transferFocus();
				}
			}
		});

		txtSoLuong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtSoLuong.transferFocus();
				}
			}
		});

		txtGiaNhap.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtGiaNhap.transferFocus();
				}
			}
		});

		comboBoxNhaCungCap.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboBoxNhaCungCap.transferFocus();
				}
			}
		});

		txtImei.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtImei.transferFocus();
				}
			}
		});

	}

	private void saveItem() {
		Integer itemId = -1;
		String name = txtTenSanPham.getText();
		String type = comboBoxLoai.getSelectedItem().toString();
		int quantity = Integer.parseInt(txtSoLuong.getText());
		String price = txtGiaNhap.getText();
		String provider = "";
		if (comboBoxNhaCungCap.getSelectedItem() != null) {
			provider = comboBoxNhaCungCap.getSelectedItem().toString();
		}
		String imei = "";
		imei = txtImei.getText().trim();
		StringTokenizer st = new StringTokenizer(imei, " ");
		String[] imeis = new String[quantity];
		if (checkData(name, type, quantity + "", price, provider, imei)) {
			if (st.countTokens() != quantity) {
				JOptionPane.showMessageDialog(null, "Dữ liệu sai");
				return;
			} else {
				for (int i = 0; i < imeis.length; i++) {
					System.out.println("length " + imeis.length);
					System.out.println("i = " + i);
					String nextToken = st.nextToken();
					System.out.println(nextToken);
					imeis[i] = nextToken;
				}

				if (txtTenSanPham.isEditable()) {
					Item item = new Item();
					item.setName(name);
					item.setType(type);
					itemId = ItemDAO.getNextId();
					item.setItemId(itemId);
					item.setQuantity(quantity);
					item.setPrice(price);
					Data.listItem.add(item);
				} else {
					Item item = Data.getItemByName(name);
					itemId = item.getItemId();
					// item.setItemId(itemId);
					item.setQuantity(item.getQuantity() + quantity);
					int totalPrice = 0;
					List<ItemDetail> listDetail = Data.getDetails(itemId);
					for (ItemDetail id : listDetail) {
						totalPrice += Integer.parseInt(id.getImportPrice());
					}

					int averagePrice = 0;
					if (Integer.parseInt(item.getPrice()) == 0) {
						averagePrice = Integer.parseInt(price);
					} else {
						averagePrice = (totalPrice + (Integer.parseInt(price) * quantity)) / (listDetail.size() + quantity);
					}
					item.setPrice(String.valueOf(averagePrice));
					Data.updateItem(item);
				}

				ItemDetail itemDetail;
				for (int i = 0; i < quantity; i++) {
					itemDetail = new ItemDetail();
					itemDetail.setItemId(itemId);
					itemDetail.setImei(imeis[i]);
					itemDetail.setImportDate(Convert.formatDateSQL(new Date()));
					itemDetail.setImportPrice(price);
					itemDetail.setProvider(Data.getCustomerByName(provider, false).getCustomerId());
					itemDetail.setStatus(true);
					Data.listItemDetail.add(itemDetail);
				}
				JOptionPane.showMessageDialog(null, "Lưu thành công");
				return;
			}
		}
	}

	private boolean checkData(String name, String type, String quantity, String price, String provider, String imei) {
		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tên sản phẩm");
			return false;
		} else if (type.equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa chọn chủng loại");
			return false;
		} else if (price.equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập giá");
			return false;
		} else if (provider.equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa chọn Nhà cung cấp");
			return false;
		} else if (quantity.equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập số lượng");
			return false;
		} else if (imei.equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập imei");
			return false;
		}

		return true;
	}

	private void disposeFrame() {
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
		Action escapeAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);
	}

}
