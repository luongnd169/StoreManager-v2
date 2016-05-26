package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

import controller.ComboBoxModel;
import controller.CustomerTableModel;
import controller.FeeTableModel;
import controller.ItemTableModel;
import controller.MainController;
import dao.CustomerDAO;
import dao.FeeDAO;
import dao.ItemDAO;
import dao.ItemDetailDAO;
import data.Data;
import lib.Convert;
import model.Customer;
import model.Fee;
import model.Item;
import model.ItemDetail;

public class Main {

	private JFrame frame;
	private JTable tableTonKho;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxTimSP, comboBoxSoLuong, comboBoxImei, comboKho, comboBoxSanPhamNhap, comboBoxNCC,
			comboBoxLoaiNhap;
	@SuppressWarnings("rawtypes")
	private static JComboBox comboBoxKhachHang;
	ComboBoxModel model;
	MainController controller;
	private List<Item> listSaleItem = new ArrayList<Item>();
	private List<ItemDetail> listSaleItemDetail = new ArrayList<>();
	private JTable tableXuat;
	private JTextField txtGiaNhap;
	private JTextField txtGiaXuat;
	private JTextField txtLoiNhuan;
	int countQuantity = 0;
	int countImei = 0;
	private JTextField txtTongTien;
	private JRadioButton rdbtnTenSP;
	private JRadioButton rdbtnSoImei;
	int warehouse;
	private JTextField txtSearch;
	private JTextField txtTongTienKho;
	private List<Item> listStorage;
	private List<Fee> listFee;
	private int tongTienKho;
	private static JTextField txtDienThoai;
	private JTextField txtMuc;
	private JTextField txtSoTien;
	private JTextField txtFrom;
	private JTextField txtTo;
	private JTable tableLichSu;
	private JTextField txtTongThu;
	private JTextField txtTongChi;
	private JTextField txtLoiNhuanThuChi;
	private JRadioButton rdbtnThu;
	private JRadioButton rdbtnChi;
	private String from;
	private String to;
	private static JTextField txtDiaChi;
	private EditItem editItem;
	private JPopupMenu popup;
	private AddItem addItem;
	private JCheckBox chckbxM;
	List<Item> test = new ArrayList<>();
	private JTextField txtTimThongTin;
	private JTable tableThongTin;
	@SuppressWarnings("unused")
	private AddCustomer addCustomer;
	private List<Customer> listProvider;
	private JButton btnTaoMoiKho;
	private JTextField txtGiaNhapNhap;
	private JTextField txtTongTienNhap;
	private JTextField txtSDTNhap;
	private JTextField txtDiaChiNhap;
	private JTextField txtSoLuongNhap;
	private JTextField txtImeiNhap;
	private JButton btnThemNhap;
	private JButton btnLuuNhap;
	private JCheckBox checkBoxNhap;
	private JButton btnHuyNhap;
	private static List<String> types;
	private String totalImportPrice;
	private List<Item> listImportItem = new ArrayList<>();
	private List<ItemDetail> listImportItemDetail = new ArrayList<>();
	private JTable tableNhap;

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxTimSP() {
		return comboBoxTimSP;
	}

	public void setComboBoxTimSP(@SuppressWarnings("rawtypes") JComboBox comboBoxTimSP) {
		this.comboBoxTimSP = comboBoxTimSP;
	}

	public JTable getTableTonKho() {
		return tableTonKho;
	}

	public void setTableTonKho(JTable tableTonKho) {
		this.tableTonKho = tableTonKho;
	}

	public JTable getTableXuat() {
		return tableXuat;
	}

	public void setTableXuat(JTable tableXuat) {
		this.tableXuat = tableXuat;
	}

	public static List<String> getTypes() {
		return types;
	}

	@SuppressWarnings("static-access")
	public void setTypes(List<String> types) {
		this.types = types;
	}

	/**
	 * Launch the application.
	 * 
	 * @throws InterruptedException
	 * @throws InvocationTargetException
	 */
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initData();
		controller = new MainController();
		initialize();
		initKho();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 0, 774, 560);
		panel.add(tabbedPane);

		JPanel panelTonKho = new JPanel();
		tabbedPane.addTab("Tồn kho", null, panelTonKho, null);
		panelTonKho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetStorage(e);
			}
		});
		panelTonKho.setBounds(10, 0, 774, 560);
		panelTonKho.setLayout(null);

		JLabel lblSearch = new JLabel("Tìm kiếm");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearch.setBounds(10, 35, 100, 30);
		panelTonKho.add(lblSearch);

		tableTonKho = new JTable();
		tableTonKho.setBounds(10, 100, 750, 375);
		tableTonKho.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPaneTonKho = new JScrollPane(tableTonKho);
		scrollPaneTonKho.setBounds(10, 119, 750, 375);
		try {
			tableTonKho.setModel(new ItemTableModel(Data.listItem) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		panelTonKho.add(scrollPaneTonKho);

		popup = new JPopupMenu();

		tableTonKho.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				checkPopup(e);
			}

			public void mouseClicked(MouseEvent e) {
				checkPopup(e);
			}

			public void mouseReleased(MouseEvent e) {
				checkPopup(e);
			}

			private void checkPopup(MouseEvent e) {
				if (tableTonKho.getSelectedRow() != -1) {
					if (e.isPopupTrigger()) {
						popup.show(tableTonKho, e.getX(), e.getY());
					}
				}
			}
		});
		initPopup(popup);

		rdbtnTenSP = new JRadioButton("Tên sản phẩm");
		rdbtnTenSP.setBounds(120, 7, 109, 23);
		panelTonKho.add(rdbtnTenSP);
		rdbtnTenSP.setSelected(true);

		rdbtnSoImei = new JRadioButton("Số imei");
		rdbtnSoImei.setBounds(231, 7, 109, 23);
		panelTonKho.add(rdbtnSoImei);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnSoImei);
		group.add(rdbtnTenSP);

		JLabel lblKho = new JLabel("Kho");
		lblKho.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKho.setBounds(529, 11, 50, 25);
		panelTonKho.add(lblKho);

		comboKho = new JComboBox();
		comboKho.setBounds(580, 14, 100, 20);
		comboKho.addItem("Tất cả");
		ItemListener itemListener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				selectStorageEvent(e);

			}
		};
		comboKho.addItemListener(itemListener);
		panelTonKho.add(comboKho);

		txtSearch = new JTextField();
		txtSearch.setBounds(120, 35, 200, 30);
		panelTonKho.add(txtSearch);
		txtSearch.setColumns(10);

		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchItemStorageEvent(e);
			}
		});

		JLabel lblNewLabel = new JLabel("Tổng tiền");
		lblNewLabel.setBounds(561, 507, 50, 20);
		panelTonKho.add(lblNewLabel);

		txtTongTienKho = new JTextField();
		txtTongTienKho.setEditable(false);
		txtTongTienKho.setBounds(628, 507, 132, 20);

		txtTongTienKho.setText(Convert.numberToString(String.valueOf(tongTienKho)));
		panelTonKho.add(txtTongTienKho);
		txtTongTienKho.setColumns(10);

		btnTaoMoiKho = new JButton("Tạo mới");
		btnTaoMoiKho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String storage = JOptionPane.showInputDialog(null, "Nhập tên kho muốn tạo");
				int flag = 0;
				for (int i = 0; i < getTypes().size(); i++) {
					if (getTypes().get(i).equals(storage)) {
					} else {
						flag++;
					}
				}
				if (flag == getTypes().size()) {
					getTypes().add(storage);
				}
				initKho();
			}
		});
		btnTaoMoiKho.setBounds(690, 13, 70, 23);
		panelTonKho.add(btnTaoMoiKho);

		JButton btnThemMoi = new JButton("Thêm mới");
		btnThemMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addItem = new AddItem();
			}
		});
		btnThemMoi.setBounds(10, 85, 89, 23);
		panelTonKho.add(btnThemMoi);

		// JButton btnThmSnPhm = new JButton("Thêm sản phẩm");
		// btnThmSnPhm.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		//
		// }
		// });
		// btnThmSnPhm.setBounds(638, 7, 109, 23);
		// panelTonKho.add(btnThmSnPhm);

		JPanel panelNhapXuat = new JPanel();
		tabbedPane.addTab("Nhập/Xuất", null, panelNhapXuat, null);
		panelNhapXuat.setLayout(null);

		JTabbedPane tabbedPaneNhapXuat = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneNhapXuat.setBounds(0, 0, 769, 532);
		panelNhapXuat.add(tabbedPaneNhapXuat);

		JPanel panelXuat = new JPanel();

		tabbedPaneNhapXuat.addTab("Xuất", null, panelXuat, null);
		panelXuat.setLayout(null);

		JLabel lblName = new JLabel("Sản phẩm");
		lblName.setBounds(10, 13, 62, 17);
		panelXuat.add(lblName);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxTimSP = new JComboBox<>();
		comboBoxTimSP.setBounds(82, 11, 240, 24);
		panelXuat.add(comboBoxTimSP);
		comboBoxTimSP.setEditable(true);
		comboBoxTimSP.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchItemSell(e);
			}
		});

		tableXuat = new JTable();
		tableXuat.setBounds(10, 150, 745, 262);
		JScrollPane scrollPaneXuat = new JScrollPane(tableXuat);
		scrollPaneXuat.setBounds(10, 157, 750, 262);
		tableXuat.setEditingColumn(3);
		panelXuat.add(scrollPaneXuat);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(332, 12, 72, 23);

		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addItemSell();
			}
		});
		panelXuat.add(btnThem);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveSaveBill();
			}
		});
		btnLuu.setBounds(602, 470, 89, 23);
		panelXuat.add(btnLuu);

		JLabel lblSoLuong = new JLabel("Số lượng\r\n");
		lblSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoLuong.setBounds(10, 61, 62, 17);
		panelXuat.add(lblSoLuong);

		comboBoxSoLuong = new JComboBox();
		comboBoxSoLuong.setBounds(82, 61, 50, 20);
		panelXuat.add(comboBoxSoLuong);

		JLabel lblGiaNhap = new JLabel("Giá nhập");
		lblGiaNhap.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiaNhap.setBounds(10, 115, 62, 17);
		panelXuat.add(lblGiaNhap);

		JLabel lblGiXuat = new JLabel("Giá xuất");
		lblGiXuat.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiXuat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiXuat.setBounds(178, 115, 62, 17);
		panelXuat.add(lblGiXuat);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setEditable(false);
		txtGiaNhap.setBounds(82, 115, 86, 20);
		panelXuat.add(txtGiaNhap);
		txtGiaNhap.setColumns(10);

		txtGiaXuat = new JTextField();
		txtGiaXuat.setBounds(250, 115, 86, 20);
		txtGiaXuat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				countProfits();
			}
		});
		panelXuat.add(txtGiaXuat);
		txtGiaXuat.setColumns(10);

		JLabel lblLoiNhuan = new JLabel("Lợi nhuận");
		lblLoiNhuan.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoiNhuan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoiNhuan.setBounds(342, 115, 62, 17);
		panelXuat.add(lblLoiNhuan);

		txtLoiNhuan = new JTextField();
		txtLoiNhuan.setEditable(false);
		txtLoiNhuan.setBounds(414, 115, 86, 20);

		panelXuat.add(txtLoiNhuan);
		txtLoiNhuan.setColumns(10);

		JLabel lblTongTien = new JLabel("Tổng tiền");
		lblTongTien.setBounds(549, 429, 46, 14);
		panelXuat.add(lblTongTien);

		txtTongTien = new JTextField();
		txtTongTien.setEditable(false);
		txtTongTien.setBounds(631, 426, 110, 20);
		panelXuat.add(txtTongTien);
		txtTongTien.setColumns(10);

		JLabel lblKhachHang = new JLabel("Khách hàng");
		lblKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKhachHang.setBounds(525, 11, 80, 17);
		panelXuat.add(lblKhachHang);

		JLabel lblDienThoai = new JLabel("Điện thoại");
		lblDienThoai.setHorizontalAlignment(SwingConstants.LEFT);
		lblDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDienThoai.setBounds(525, 61, 62, 17);
		panelXuat.add(lblDienThoai);

		txtDienThoai = new JTextField();
		txtDienThoai.setColumns(10);
		txtDienThoai.setBounds(615, 61, 90, 20);
		txtDienThoai.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String phone = txtDienThoai.getText().trim();
					findCustomer(phone);
				}
			}
		});
		panelXuat.add(txtDienThoai);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiaChi.setBounds(525, 115, 50, 17);
		panelXuat.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(615, 115, 90, 20);
		panelXuat.add(txtDiaChi);

		comboBoxKhachHang = new JComboBox();
		comboBoxKhachHang.setBounds(615, 11, 90, 20);
		List<Customer> listCustomer = CustomerDAO.getCustomer("From Customer where provider = 0");
		if (!listCustomer.isEmpty()) {
			for (Customer c : listCustomer) {
				comboBoxKhachHang.addItem(c.getName());
			}
			txtDienThoai.setText(listCustomer.get(0).getPhone());
			txtDiaChi.setText(listCustomer.get(0).getAddress());
		}
		comboBoxKhachHang.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxKhachHang.getSelectedIndex() != -1) {
					showCustomer();
				}

			}
		});
		panelXuat.add(comboBoxKhachHang);

		chckbxM = new JCheckBox("Mới");
		chckbxM.setBounds(710, 9, 50, 23);
		chckbxM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkboxCustomerEvent();

			}
		});
		panelXuat.add(chckbxM);

		JLabel lblImei = new JLabel("Imei");
		lblImei.setHorizontalAlignment(SwingConstants.CENTER);
		lblImei.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblImei.setBounds(178, 64, 62, 17);
		panelXuat.add(lblImei);

		comboBoxImei = new JComboBox();
		comboBoxImei.setBounds(250, 61, 154, 20);
		panelXuat.add(comboBoxImei);
		comboBoxImei.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				generateImportPrice();
			}
		});

		JButton btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelSell();
			}
		});
		btnHuy.setBounds(101, 470, 89, 23);
		panelXuat.add(btnHuy);

		JPanel panelNhap = new JPanel();
		panelNhap.setLayout(null);
		tabbedPaneNhapXuat.addTab("Nhập", null, panelNhap, null);

		JLabel lblSanPhamNhap = new JLabel("Sản phẩm");
		lblSanPhamNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblSanPhamNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSanPhamNhap.setBounds(10, 13, 62, 17);
		panelNhap.add(lblSanPhamNhap);

		comboBoxSanPhamNhap = new JComboBox();
		comboBoxSanPhamNhap.setEditable(true);
		comboBoxSanPhamNhap.setBounds(82, 11, 240, 24);
		comboBoxSanPhamNhap.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchImportItem(e);
			}
		});
		panelNhap.add(comboBoxSanPhamNhap);

		JScrollPane scrollPaneNhap = new JScrollPane((Component) null);
		scrollPaneNhap.setBounds(10, 157, 750, 262);

		panelNhap.add(scrollPaneNhap);

		tableNhap = new JTable();
		scrollPaneNhap.setViewportView(tableNhap);

		btnThemNhap = new JButton("Thêm");
		btnThemNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addImportItem();
			}
		});
		btnThemNhap.setBounds(332, 12, 72, 23);
		panelNhap.add(btnThemNhap);

		btnLuuNhap = new JButton("Lưu");
		btnLuuNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveImportBill();
			}
		});
		btnLuuNhap.setBounds(602, 470, 89, 23);
		panelNhap.add(btnLuuNhap);

		JLabel lblSoLuongNhap = new JLabel("Số lượng\r\n");
		lblSoLuongNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoLuongNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoLuongNhap.setBounds(10, 61, 62, 17);
		panelNhap.add(lblSoLuongNhap);

		JLabel lblGiaNhapNhap = new JLabel("Giá nhập");
		lblGiaNhapNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaNhapNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiaNhapNhap.setBounds(194, 115, 62, 17);
		panelNhap.add(lblGiaNhapNhap);

		txtGiaNhapNhap = new JTextField();
		txtGiaNhapNhap.setColumns(10);
		txtGiaNhapNhap.setBounds(271, 115, 133, 20);
		panelNhap.add(txtGiaNhapNhap);

		JLabel lblTongTienNhap = new JLabel("Tổng tiền");
		lblTongTienNhap.setBounds(549, 429, 46, 14);
		panelNhap.add(lblTongTienNhap);

		txtTongTienNhap = new JTextField();
		txtTongTienNhap.setEditable(false);
		txtTongTienNhap.setColumns(10);
		txtTongTienNhap.setBounds(631, 426, 110, 20);
		panelNhap.add(txtTongTienNhap);

		JLabel lblNhaCungCap = new JLabel("Nhà cung cấp");
		lblNhaCungCap.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhaCungCap.setBounds(499, 13, 96, 17);
		panelNhap.add(lblNhaCungCap);

		JLabel lblSDTNhap = new JLabel("Điện thoại");
		lblSDTNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDTNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDTNhap.setBounds(499, 61, 62, 17);
		panelNhap.add(lblSDTNhap);

		txtSDTNhap = new JTextField();
		txtSDTNhap.setColumns(10);
		txtSDTNhap.setBounds(602, 61, 139, 20);
		panelNhap.add(txtSDTNhap);

		JLabel lblDiaChiNhap = new JLabel("Địa chỉ");
		lblDiaChiNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaChiNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiaChiNhap.setBounds(499, 115, 50, 17);
		panelNhap.add(lblDiaChiNhap);

		txtDiaChiNhap = new JTextField();
		txtDiaChiNhap.setEditable(false);
		txtDiaChiNhap.setColumns(10);
		txtDiaChiNhap.setBounds(602, 115, 139, 20);
		panelNhap.add(txtDiaChiNhap);

		comboBoxNCC = new JComboBox();
		comboBoxNCC.setBounds(602, 13, 90, 20);
		panelNhap.add(comboBoxNCC);
		List<Customer> listProvider = CustomerDAO.getCustomer("From Customer where provider = 1");
		if (!listProvider.isEmpty()) {
			for (Customer c : listProvider) {
				comboBoxNCC.addItem(c.getName());
			}
			txtSDTNhap.setText(listCustomer.get(0).getPhone());
			txtDiaChiNhap.setText(listCustomer.get(0).getAddress());
		}
		comboBoxNCC.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (comboBoxNCC.getSelectedIndex() != -1) {
					showProvider();
				}

			}
		});

		checkBoxNhap = new JCheckBox("Mới");
		checkBoxNhap.setBounds(710, 9, 50, 23);
		panelNhap.add(checkBoxNhap);

		JLabel lblImeiNhap = new JLabel("Imei");
		lblImeiNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblImeiNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblImeiNhap.setBounds(194, 64, 46, 17);
		panelNhap.add(lblImeiNhap);

		btnHuyNhap = new JButton("Hủy");
		btnHuyNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelImport();
			}
		});
		btnHuyNhap.setBounds(101, 470, 89, 23);
		panelNhap.add(btnHuyNhap);

		txtSoLuongNhap = new JTextField();
		txtSoLuongNhap.setBounds(82, 60, 86, 20);
		panelNhap.add(txtSoLuongNhap);
		txtSoLuongNhap.setColumns(10);

		txtImeiNhap = new JTextField();
		txtImeiNhap.setBounds(271, 61, 132, 20);
		panelNhap.add(txtImeiNhap);
		txtImeiNhap.setColumns(10);

		JLabel lblLoaiNhap = new JLabel("Loại");
		lblLoaiNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoaiNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoaiNhap.setBounds(10, 116, 62, 17);
		panelNhap.add(lblLoaiNhap);

		comboBoxLoaiNhap = new JComboBox();
		comboBoxLoaiNhap.setBounds(82, 115, 86, 20);
		panelNhap.add(comboBoxLoaiNhap);

		JPanel panelThuChi = new JPanel();
		tabbedPane.addTab("Thu/Chi", null, panelThuChi, null);
		panelThuChi.setLayout(null);

		JPanel panelGhi = new JPanel();
		panelGhi.setBounds(0, 0, 765, 182);
		panelThuChi.add(panelGhi);
		panelGhi.setLayout(null);

		JLabel lblMuc = new JLabel("Mục");
		lblMuc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMuc.setBounds(20, 20, 50, 25);
		panelGhi.add(lblMuc);

		JLabel lblSoTien = new JLabel("Số tiền");
		lblSoTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoTien.setBounds(20, 78, 50, 25);
		panelGhi.add(lblSoTien);

		txtMuc = new JTextField();
		txtMuc.setColumns(10);
		txtMuc.setBounds(95, 20, 200, 25);
		panelGhi.add(txtMuc);

		txtSoTien = new JTextField();
		txtSoTien.setColumns(10);
		txtSoTien.setBounds(95, 78, 200, 25);
		panelGhi.add(txtSoTien);
		txtSoTien.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				confirmSaveFee(e);
			}
		});

		JButton btnLu = new JButton("Lưu");
		btnLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveFee();
			}
		});
		btnLu.setBounds(705, 148, 50, 23);
		panelGhi.add(btnLu);

		rdbtnThu = new JRadioButton("Thu");
		rdbtnThu.setSelected(true);
		rdbtnThu.setBounds(632, 7, 50, 23);
		panelGhi.add(rdbtnThu);

		rdbtnChi = new JRadioButton("Chi");
		rdbtnChi.setBounds(705, 7, 50, 23);
		panelGhi.add(rdbtnChi);

		ButtonGroup groupThuChi = new ButtonGroup();
		groupThuChi.add(rdbtnThu);
		groupThuChi.add(rdbtnChi);

		JPanel panelLichSu = new JPanel();
		panelLichSu.setBounds(0, 185, 765, 345);
		panelThuChi.add(panelLichSu);
		panelLichSu.setLayout(null);

		JLabel lblLichSu = new JLabel("Lịch sử");
		lblLichSu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLichSu.setBounds(10, 11, 50, 25);
		panelLichSu.add(lblLichSu);

		JLabel lblFrom = new JLabel("Từ");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFrom.setBounds(70, 24, 50, 25);
		panelLichSu.add(lblFrom);

		txtFrom = new JTextField();
		txtFrom.setColumns(10);
		txtFrom.setBounds(130, 23, 170, 25);
		txtFrom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchFeeFrom(e);
			}
		});
		panelLichSu.add(txtFrom);

		JLabel lblTo = new JLabel("Đến");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTo.setBounds(346, 24, 50, 25);
		panelLichSu.add(lblTo);

		txtTo = new JTextField();
		txtTo.setColumns(10);
		txtTo.setBounds(406, 23, 170, 25);
		txtTo.setText(Convert.formatDate(new Date()));
		txtTo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchFeeTo(e);
			}
		});
		panelLichSu.add(txtTo);

		tableLichSu = new JTable();
		tableLichSu.setBounds(10, 344, 745, 262);
		tableLichSu.setModel(new FeeTableModel(listFee));

		JScrollPane scrollPaneLichSu = new JScrollPane(tableLichSu);
		scrollPaneLichSu.setBounds(10, 72, 745, 215);
		panelLichSu.add(scrollPaneLichSu);

		JLabel lblTongThu = new JLabel("Tổng thu");
		lblTongThu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTongThu.setBounds(46, 309, 60, 14);
		panelLichSu.add(lblTongThu);

		txtTongThu = new JTextField();
		txtTongThu.setEditable(false);
		txtTongThu.setBounds(130, 306, 90, 22);
		panelLichSu.add(txtTongThu);
		txtTongThu.setColumns(10);

		JLabel lblTongChi = new JLabel("Tổng chi");
		lblTongChi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTongChi.setBounds(278, 309, 60, 14);
		panelLichSu.add(lblTongChi);

		txtTongChi = new JTextField();
		txtTongChi.setEditable(false);
		txtTongChi.setColumns(10);
		txtTongChi.setBounds(362, 306, 90, 22);
		panelLichSu.add(txtTongChi);

		JLabel lblLoiNhuanThuChi = new JLabel("Lợi nhuận");
		lblLoiNhuanThuChi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoiNhuanThuChi.setBounds(516, 309, 60, 14);
		panelLichSu.add(lblLoiNhuanThuChi);

		txtLoiNhuanThuChi = new JTextField();
		txtLoiNhuanThuChi.setEditable(false);
		txtLoiNhuanThuChi.setColumns(10);
		txtLoiNhuanThuChi.setBounds(600, 306, 90, 22);
		panelLichSu.add(txtLoiNhuanThuChi);
		setValues();

		JButton btnTtC = new JButton("Tất cả");
		btnTtC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showAllFees();
			}
		});
		btnTtC.setBounds(692, 27, 63, 23);
		panelLichSu.add(btnTtC);

		JPanel panelThongTin = new JPanel();
		tabbedPane.addTab("Thông tin", null, panelThongTin, null);
		panelThongTin.setLayout(null);

		JLabel lblTimThongTin = new JLabel("Tìm kiếm");
		lblTimThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimThongTin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimThongTin.setBounds(10, 49, 100, 30);
		panelThongTin.add(lblTimThongTin);

		JRadioButton rdbtnKhachHang = new JRadioButton("Khách hàng");
		rdbtnKhachHang.setSelected(true);
		rdbtnKhachHang.setBounds(120, 24, 109, 23);
		panelThongTin.add(rdbtnKhachHang);

		txtTimThongTin = new JTextField();
		txtTimThongTin.setColumns(10);
		txtTimThongTin.setBounds(120, 49, 200, 30);
		panelThongTin.add(txtTimThongTin);

		JRadioButton rdbtnNCC = new JRadioButton("Nhà cung cấp");
		rdbtnNCC.setBounds(231, 24, 109, 23);
		panelThongTin.add(rdbtnNCC);

		JButton btnThemKhachHang = new JButton("Thêm mới");
		btnThemKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomer = new AddCustomer(tableThongTin);
			}
		});
		btnThemKhachHang.setBounds(665, 55, 89, 23);
		panelThongTin.add(btnThemKhachHang);

		ButtonGroup groupThongTin = new ButtonGroup();
		groupThongTin.add(rdbtnKhachHang);
		groupThongTin.add(rdbtnNCC);

		tableThongTin = new JTable();
		tableThongTin.setBounds(10, 100, 750, 375);
		tableThongTin.getTableHeader().setReorderingAllowed(false);
		tableThongTin.setModel(new CustomerTableModel(CustomerDAO.getCustomers()));
		JScrollPane scrollPaneThongTin = new JScrollPane(tableThongTin);
		scrollPaneThongTin.setBounds(10, 119, 750, 395);
		panelThongTin.add(scrollPaneThongTin);

	}

	public void initData() {
		listStorage = new ArrayList<Item>();
		listStorage = ItemDAO.getItemes();

		listFee = new ArrayList<>();
		listFee = FeeDAO.getFees();
		Convert.convertListFee(listFee);

		setListProvider(CustomerDAO.getCustomer("From Customer where provider = 1"));

		for (Item i : Convert.returnListItem(listStorage)) {
			tongTienKho += Integer.parseInt(i.getPrice()) * i.getQuantity();
		}

		setTypes(ItemDAO.getTypes());

	}

	private void setValues() {
		List<Fee> list = FeeDAO.getFees();
		int tongThu = 0;
		int tongChi = 0;
		for (Fee e : list) {
			if (e.isType()) {
				tongThu += Integer.parseInt(e.getValue());
			} else {
				tongChi += Integer.parseInt(e.getValue());
			}
		}
		int loiNhuan = tongThu - tongChi;
		txtTongThu.setText(Convert.numberToString(tongThu + ""));
		txtTongChi.setText(Convert.numberToString(tongChi + ""));
		txtLoiNhuanThuChi.setText(Convert.numberToString(loiNhuan + ""));
	}

	private void initPopup(JPopupMenu popup) {
		ActionListener menuListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Thêm")) {
					addItem = new AddItem();
					addItem.getComboBoxLoai().transferFocus();
					addItem.getComboBoxLoai().setEnabled(false);
					addItem.getTxtTenSanPham()
							.setText(ItemTableModel.listItem.get(tableTonKho.getSelectedRow()).getName());
					addItem.getTxtTenSanPham().setEditable(false);
				} else if (e.getActionCommand().equals("Sửa")) {
					Item selectedItem = listStorage.get(tableTonKho.getSelectedRow());
					selectedItem = ItemDAO.getItem(selectedItem.getItemId());
					List<ItemDetail> listDetail = ItemDetailDAO.getItemDetail(
							"From ItemDetail where itemId = " + selectedItem.getItemId() + " and status = 1");
					editItem = new EditItem(listDetail);
					editItem.getTxtMaSP().transferFocus();
					editItem.getTxtMaSP().setEditable(false);
					editItem.getTxtMaSP().setText(selectedItem.getItemId() + "");
					editItem.getTxtTenSP().setText(selectedItem.getName());
					editItem.getTxtSoLuongTon().setText(selectedItem.getQuantity() + "");
					editItem.getTxtGiaBinhQuan().setText(selectedItem.getPrice());

				} else if (e.getActionCommand().equals("Xóa")) {

				}

			}
		};

		JMenuItem item;
		popup.add(item = new JMenuItem("Thêm"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.addActionListener(menuListener);

		popup.add(item = new JMenuItem("Sửa"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.addActionListener(menuListener);

		popup.add(item = new JMenuItem("Xóa"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.addActionListener(menuListener);

	}

	private void resetStorage(MouseEvent e) {
		Data.listItem.addAll(listStorage);
		tableTonKho.setModel(new ItemTableModel(listStorage));
		if (comboKho.getSelectedItem().toString().equals("Tất cả")) {
			Data.listItem.addAll(listStorage);
			tableTonKho.setModel(new ItemTableModel(listStorage));
			tongTienKho = 0;
			for (Item i : listStorage) {
				tongTienKho += Integer.parseInt(i.getPrice()) * i.getQuantity();
			}
			txtTongTienKho.setText(Convert.numberToString(String.valueOf(tongTienKho)));
		} else {
			String type = comboKho.getSelectedItem().toString();
			listStorage = Data.getItemByType(type);
			tableTonKho.setModel(new ItemTableModel(listStorage));
			tongTienKho = 0;
			for (Item i : listStorage) {
				tongTienKho += Integer.parseInt(i.getPrice()) * i.getQuantity();
			}
			txtTongTienKho.setText(Convert.numberToString(String.valueOf(tongTienKho)));
		}
	}

	private void selectStorageEvent(ItemEvent e) {
		if (e.getItem().equals("Tất cả")) {
			Data.listItem.addAll(listStorage);
			tableTonKho.setModel(new ItemTableModel(listStorage));
			tongTienKho = 0;
			for (Item i : listStorage) {
				tongTienKho += Integer.parseInt(i.getPrice()) * i.getQuantity();
			}
			txtTongTienKho.setText(Convert.numberToString(String.valueOf(tongTienKho)));
		} else {
			String type = e.getItem().toString();
			listStorage = Data.getItemByType(type);
			tableTonKho.setModel(new ItemTableModel(listStorage));
			tongTienKho = 0;
			for (Item i : Convert.returnListItem(listStorage)) {
				tongTienKho += Integer.parseInt(i.getPrice()) * i.getQuantity();
			}
			txtTongTienKho.setText(Convert.numberToString(String.valueOf(tongTienKho)));
		}
	}

	private void searchItemStorageEvent(KeyEvent e) {
		if (txtSearch.getText().trim().equals("")) {
			Data.listItem.addAll(listStorage);
			tableTonKho.setModel(new ItemTableModel(listStorage));
		} else {
			if (rdbtnTenSP.isSelected()) {
				listStorage = controller.searchItemByName(ItemDAO.getItemes(), txtSearch.getText());
				tableTonKho.setModel(new ItemTableModel(listStorage));
			} else {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					listStorage = controller.searchItemByImei(txtSearch.getText());
					tableTonKho.setModel(new ItemTableModel(listStorage));
				}
			}
		}
	}

	private void countProfits() {
		txtLoiNhuan.setText(Convert.numberToString(String.valueOf(Integer.parseInt(txtGiaXuat.getText())
				- Integer.parseInt(Convert.stringToNumber(txtGiaNhap.getText())))));
	}

	@SuppressWarnings("unchecked")
	private void searchItemSell(KeyEvent e) {
		String price = "";
		int quantity = 0;
		model = new ComboBoxModel();
		String name = comboBoxTimSP.getEditor().getItem().toString();
		if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90 || e.getKeyCode() >= 96 && e.getKeyCode() <= 105
				|| e.getKeyCode() == 8) {
			comboBoxTimSP.setModel(model.getList(name));
			comboBoxTimSP.showPopup();
			comboBoxTimSP.getEditor().setItem(name);
			((JTextComponent) comboBoxTimSP.getEditor().getEditorComponent()).select(name.length(), name.length());
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			countQuantity = comboBoxSoLuong.getItemCount();
			for (int i = 0; i < countQuantity; i++) {
				comboBoxSoLuong.removeItemAt(0);
			}
			comboBoxImei.removeAllItems();
			Item item = Data.getItemByName(name);
			if (item != null) {
				quantity = item.getQuantity();
				for (int i = 1; i <= quantity; i++) {
					comboBoxSoLuong.addItem(i);
				}

			}
			List<ItemDetail> listDetail = Data.getDetails(item.getItemId());
			price = listDetail.get(0).getImportPrice();
			txtGiaNhap.setText(Convert.numberToString(price));
			for (ItemDetail id : listDetail) {
				if (id.isStatus()) {
					comboBoxImei.addItem(id.getImei());
				}
			}

		}
	}

	private void addItemSell() {
		String name = comboBoxTimSP.getEditor().getItem().toString();
		String imei = comboBoxImei.getSelectedItem().toString();
		Item i = Data.getItemByName(name);
		ItemDetail id = Data.getItemDetailByImei(imei);
		listSaleItemDetail.add(id);
		i.setQuantity(Integer.parseInt(comboBoxSoLuong.getSelectedItem().toString()));
		if (!txtGiaXuat.getText().trim().equals("")) {
			i.setPrice(txtGiaXuat.getText().trim());
			listSaleItem.add(i);
			i.setPrice(Convert.numberToString(txtGiaXuat.getText().trim()));
			tableXuat.setModel(new ItemTableModel(listSaleItem));
			int total = 0;
			for (Item item : listSaleItem) {
				total += Integer.parseInt(Convert.stringToNumber(item.getPrice())) * item.getQuantity();
			}
			txtTongTien.setText(Convert.numberToString(String.valueOf(total)));

		} else {
			JOptionPane.showMessageDialog(null, "Chưa điền giá xuất", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void saveSaveBill() {
		Customer c = new Customer();
		c.setName(comboBoxKhachHang.getSelectedItem().toString());
		c.setPhone(txtDienThoai.getText());
		c.setAddress(txtDiaChi.getText());
		c.setProvider(false);
		if (chckbxM.isSelected()) {
			Data.listPerson.add(c);
		} else {
			c.setCustomerId(Data.getCustomerByPhone(c.getName()).getCustomerId());
		}
		controller = new MainController();
		controller.saveBill(listSaleItem, listSaleItemDetail, c, "X");
		JOptionPane.showMessageDialog(null, "Lưu hóa đơn thành công");
		listSaleItem.removeAll(listSaleItem);
		listSaleItemDetail.removeAll(listSaleItemDetail);
		tableXuat.setModel(new ItemTableModel(Convert.convertListItem(listSaleItem)));
		tableTonKho.setModel(new ItemTableModel(Convert.convertListItem(ItemDAO.getItemes())));
		tableThongTin.setModel(new CustomerTableModel(CustomerDAO.getCustomers()));
		clearSale();
	}

	public void clearSale() {
		comboBoxTimSP.removeAllItems();
		comboBoxSoLuong.removeAllItems();
		comboBoxImei.removeAllItems();
		txtGiaNhap.setText("");
		txtGiaXuat.setText("");
		txtLoiNhuan.setText("");
		txtTongTien.setText("");
	}

	private void generateImportPrice() {
		String imei = comboBoxImei.getSelectedItem().toString();
		System.out.println("imei = " + imei);
		String importPrice = ItemDetailDAO.getItemDetail("From ItemDetail where imei = '" + imei + "'").get(0)
				.getImportPrice();
		txtGiaNhap.setText(Convert.numberToString(importPrice));
	}

	@SuppressWarnings("unchecked")
	private void searchImportItem(KeyEvent e) {
		model = new ComboBoxModel();
		String name = comboBoxSanPhamNhap.getEditor().getItem().toString();
		System.out.println(name);
		if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90 || e.getKeyCode() >= 96 && e.getKeyCode() <= 105
				|| e.getKeyCode() == 8) {
			comboBoxSanPhamNhap.setModel(model.getList(name));
			comboBoxSanPhamNhap.showPopup();
			comboBoxSanPhamNhap.getEditor().setItem(name);
			((JTextComponent) comboBoxSanPhamNhap.getEditor().getEditorComponent()).select(name.length(),
					name.length());
		}
	}

	private void addImportItem() {
		String name = comboBoxSanPhamNhap.getEditor().getItem().toString();
		int quantity = Integer.parseInt(txtSoLuongNhap.getText());
		String type = comboBoxLoaiNhap.getSelectedItem().toString();
		String imei = "";
		if (type.equals("Điện thoại") || type.equals("Máy tính bảng")) {
			imei = txtImeiNhap.getText();
		}
		if (!txtGiaNhapNhap.getText().equals("")) {
			String price = txtGiaNhapNhap.getText();
			// String providerName = comboBoxNCC.getSelectedItem().toString();

			Item item = new Item();
			item.setItemId(ItemDAO.getId(name));
			item.setName(name);
			item.setType(type);
			item.setQuantity(quantity);
			item.setPrice(Convert.numberToString(price));
			listImportItem.add(item);
			tableNhap.setModel(new ItemTableModel(listImportItem));

			ItemDetail detail = new ItemDetail();
			detail.setItemId(item.getItemId());
			detail.setImportDate(Convert.formatDateSQL(new Date()));
			detail.setImportPrice(price);
			detail.setStatus(true);
			detail.setImei(imei);
			// detail.setProvider(CustomerDAO.getId(providerName));
			listImportItemDetail.add(detail);
			int total = 0;
			for (Item i : listImportItem) {
				total += Integer.parseInt(Convert.stringToNumber(i.getPrice())) * i.getQuantity();
			}
			txtTongTienNhap.setText(Convert.numberToString(String.valueOf(total)));
		} else {
			JOptionPane.showMessageDialog(null, "Chưa điền giá nhập", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void saveImportBill() {
		Customer c = new Customer();
		c.setName(comboBoxNCC.getSelectedItem().toString());
		c.setPhone(txtSDTNhap.getText());
		c.setAddress(txtDiaChiNhap.getText());
		c.setProvider(true);
		c.setCustomerId(CustomerDAO.getId(comboBoxNCC.getSelectedItem().toString(), "provider"));
		controller = new MainController();
		for (Item i : listImportItem) {
			i.setPrice(Convert.stringToNumber(i.getPrice()));
		}
		controller.saveBill(listImportItem, listImportItemDetail, c, "N");
		JOptionPane.showMessageDialog(null, "Lưu hóa đơn thành công");
		listImportItem.removeAll(listImportItem);
		listImportItemDetail.removeAll(listImportItemDetail);
		tableNhap.setModel(new ItemTableModel(Convert.convertListItem(listSaleItem)));
		tableTonKho.setModel(new ItemTableModel(Convert.convertListItem(ItemDAO.getItemes())));
		clearImport();

	}

	private void clearImport() {
		comboBoxSanPhamNhap.removeAllItems();
		txtSoLuongNhap.setText("");
		txtImeiNhap.setText("");
		txtGiaNhapNhap.setText("");
		txtTongTienNhap.setText("");

	}

	private void showCustomer() {
		if (!chckbxM.isSelected()) {
			Customer customer = CustomerDAO
					.getCustomer("From Customer where name = '" + comboBoxKhachHang.getSelectedItem().toString() + "'")
					.get(0);
			if (customer != null) {
				txtDienThoai.setText(customer.getPhone());
				txtDiaChi.setText(customer.getAddress());
			}
		}
	}

	private void showProvider() {
		Customer provider = CustomerDAO
				.getCustomer("From Customer where name = '" + comboBoxNCC.getSelectedItem().toString() + "'").get(0);
		if (provider != null) {
			txtSDTNhap.setText(provider.getPhone());
			txtDiaChiNhap.setText(provider.getAddress());
		}

	}

	@SuppressWarnings("unchecked")
	private void checkboxCustomerEvent() {
		if (chckbxM.isSelected()) {
			comboBoxKhachHang.setEditable(true);
			comboBoxKhachHang.removeAllItems();
			txtDienThoai.setText("");
			txtDiaChi.setText("");
			txtDiaChi.setEditable(true);
		} else {
			comboBoxKhachHang.setEditable(false);
			List<Customer> listCustomer = CustomerDAO.getCustomers();
			if (!listCustomer.isEmpty()) {
				for (Customer c : listCustomer) {
					comboBoxKhachHang.addItem(c.getName());
				}
				txtDienThoai.setText(listCustomer.get(0).getPhone());
				txtDiaChi.setText(listCustomer.get(0).getAddress());
				txtDiaChi.setEditable(false);
			}
		}
	}

	private void cancelSell() {
		clearSale();
		listSaleItem = new ArrayList<>();
		listSaleItemDetail = new ArrayList<>();
		tableXuat.setModel(new ItemTableModel(listSaleItem));
	}

	private void cancelImport() {
		clearImport();
		listImportItem = new ArrayList<>();
		listImportItemDetail = new ArrayList<>();
		tableNhap.setModel(new ItemTableModel(listImportItem));
	}

	private void saveFee() {
		Fee fee = new Fee();
		String name = txtMuc.getText().toString().trim();
		String value = txtSoTien.getText().toString().trim();
		if (!(name.equals("") && value.equals(""))) {
			fee.setName(name);
			try {
				Integer.parseInt(value);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Dữ liệu sai");
				txtMuc.requestFocus();
				return;
			}
			fee.setValue(value);
			if (rdbtnThu.isSelected()) {
				fee.setType(true);
			} else {
				fee.setType(false);
			}
			fee.setDate(new Date());
			FeeDAO.insert(fee);
			JOptionPane.showMessageDialog(null, "Lưu thành công");
			txtMuc.setText("");
			txtSoTien.setText("");
			listFee = FeeDAO.getFees();
			tableLichSu.setModel(new FeeTableModel(Convert.convertListFee(listFee)));
			setValues();
		} else {
			txtMuc.requestFocus();
			JOptionPane.showMessageDialog(null, "Dữ liệu sai");
		}

	}

	private void searchFeeFrom(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			for (int i = 0; i < listFee.size(); i++) {
				listFee.remove(i);
			}
			from = Convert.getDate(txtFrom.getText().toString().trim());
			if (txtTo.getText().equals(Convert.formatDate(new Date()))) {
				to = Convert.getDate(Convert.formatDate(new Date()));
			} else {
				to = Convert.getDate(txtTo.getText().toString().trim());
			}
			if (from.equals("")) {
				listFee = FeeDAO.getFee("From Fee where day <= '" + to + "'");
			} else if (!FeeDAO.getByDate(from, to).isEmpty()) {
				listFee = FeeDAO.getByDate(from, to);
				tableLichSu.setModel(new FeeTableModel(Convert.convertListFee(listFee)));
			}
		}
	}

	private void searchFeeTo(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			for (int i = 0; i < listFee.size(); i++) {
				listFee.remove(i);
			}
			from = Convert.getDate(txtFrom.getText().toString().trim());
			if (txtTo.getText().equals(Convert.formatDate(new Date()))) {
				to = Convert.getDate(Convert.formatDate(new Date()));
			} else {
				to = Convert.getDate(txtTo.getText().toString().trim());
			}
			if (from.equals("")) {
				listFee = FeeDAO.getFee("From Fee where day <= '" + to + "'");
			} else if (!FeeDAO.getByDate(from, to).isEmpty()) {
				listFee = FeeDAO.getByDate(from, to);
				tableLichSu.setModel(new FeeTableModel(Convert.convertListFee(listFee)));
			}
		}
	}

	private void showAllFees() {
		listFee = FeeDAO.getFees();
		tableLichSu.setModel(new FeeTableModel(Convert.convertListFee(listFee)));
		setValues();
	}

	public List<Customer> getListProvider() {
		return listProvider;
	}

	public void setListProvider(List<Customer> listProvider) {
		this.listProvider = listProvider;
	}

	private void confirmSaveFee(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			int rep = JOptionPane.showConfirmDialog(null, "Lưu phiếu?", null, JOptionPane.YES_NO_OPTION);
			if (rep == JOptionPane.YES_OPTION) {
				saveFee();
				return;
			} else {
				txtMuc.requestFocus();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void reloadCustomer() {
		List<Customer> listCustomer = CustomerDAO.getCustomer("From Customer where provider = 0");
		if (!listCustomer.isEmpty()) {
			comboBoxKhachHang.removeAllItems();
			for (Customer c : listCustomer) {
				comboBoxKhachHang.addItem(c.getName());
			}
			txtDienThoai.setText(listCustomer.get(0).getPhone());
			txtDiaChi.setText(listCustomer.get(0).getAddress());
		}
	}

	@SuppressWarnings("unchecked")
	private void findCustomer(String phone) {
		List<Customer> list = CustomerDAO.getCustomer("From Customer where phone = '" + phone + "' and provider = 0");
		if (!list.isEmpty()) {
			Customer c = list.get(0);
			if (comboBoxKhachHang.getItemCount() != -1) {
				comboBoxKhachHang.removeAllItems();
				comboBoxKhachHang.addItem(c.getName());
			}
			txtDiaChi.setText(c.getAddress());
		}
	}

	@SuppressWarnings("unchecked")
	private void initKho() {
		if (comboKho.getItemCount() != -1) {
			comboKho.removeAllItems();
		}
		if (comboBoxLoaiNhap.getItemCount() != -1) {
			comboBoxLoaiNhap.removeAllItems();
		}
		comboKho.addItem("Tất cả");
		for (String s : getTypes()) {
			comboKho.addItem(s);
			comboBoxLoaiNhap.addItem(s);
		}
	}
}
