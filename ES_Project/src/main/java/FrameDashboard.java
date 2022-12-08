import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class FrameDashboard extends JFrame {

	private JPanel backgroundPane;
	private Image IconLogo = new ImageIcon(FrameLogin.class.getResource("Res/logo.png")).getImage().getScaledInstance(150,150,Image.SCALE_SMOOTH);
	private Image home = new ImageIcon(FrameLogin.class.getResource("Res/home.png")).getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDashboard frame = new FrameDashboard();
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
	public FrameDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 550);
		setUndecorated(true);
		backgroundPane = new JPanel();
		backgroundPane.setBackground(new Color(255, 255, 255));
		backgroundPane.setBorder(new LineBorder(new Color(0, 128, 128)));

		setContentPane(backgroundPane);
		backgroundPane.setLayout(null);
		
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBounds(0, 160, 219, 390);
		MenuPanel.setBackground(new Color(0, 128, 128));
		backgroundPane.add(MenuPanel);
		MenuPanel.setLayout(null);
		
		JPanel panelHome = new JPanel();
		panelHome.setBorder(new LineBorder(new Color(255, 255, 255)));
		panelHome.setForeground(new Color(255, 255, 255));
		panelHome.setToolTipText("");
		panelHome.setBackground(new Color(0, 128, 128));
		panelHome.setBounds(0, 35, 219, 47);
		MenuPanel.add(panelHome);
		panelHome.setLayout(null);
		
		JLabel lblLabelHome = new JLabel("Home");
		lblLabelHome.setBounds(83, 6, 65, 35);
		lblLabelHome.setForeground(new Color(255, 255, 255));
		lblLabelHome.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelHome.add(lblLabelHome);
		
		JPanel ImportPanel = new JPanel();
		ImportPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		ImportPanel.setToolTipText("");
		ImportPanel.setForeground(Color.WHITE);
		ImportPanel.setBackground(new Color(0, 128, 128));
		ImportPanel.setBounds(0, 81, 219, 47);
		MenuPanel.add(ImportPanel);
		ImportPanel.setLayout(null);
		
		JLabel lblNewLabelImport = new JLabel("Import");
		lblNewLabelImport.setForeground(Color.WHITE);
		lblNewLabelImport.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabelImport.setBounds(73, 6, 65, 35);
		ImportPanel.add(lblNewLabelImport);
		
		JPanel ViewPanel = new JPanel();
		ViewPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		ViewPanel.setToolTipText("");
		ViewPanel.setForeground(Color.WHITE);
		ViewPanel.setBackground(new Color(0, 128, 128));
		ViewPanel.setBounds(0, 127, 219, 47);
		MenuPanel.add(ViewPanel);
		ViewPanel.setLayout(null);
		
		JLabel lblViewCalendars = new JLabel("View ");
		lblViewCalendars.setForeground(Color.WHITE);
		lblViewCalendars.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblViewCalendars.setBounds(83, 6, 65, 35);
		ViewPanel.add(lblViewCalendars);
		
		JPanel ExportPanel = new JPanel();
		ExportPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		ExportPanel.setToolTipText("");
		ExportPanel.setForeground(Color.WHITE);
		ExportPanel.setBackground(new Color(0, 128, 128));
		ExportPanel.setBounds(0, 172, 219, 47);
		MenuPanel.add(ExportPanel);
		ExportPanel.setLayout(null);
		
		JLabel lblExport = new JLabel("Export");
		lblExport.setForeground(Color.WHITE);
		lblExport.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblExport.setBounds(72, 6, 65, 35);
		ExportPanel.add(lblExport);
		
		JPanel ToDoPanel = new JPanel();
		ToDoPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		ToDoPanel.setToolTipText("");
		ToDoPanel.setForeground(Color.WHITE);
		ToDoPanel.setBackground(new Color(0, 128, 128));
		ToDoPanel.setBounds(0, 218, 219, 47);
		MenuPanel.add(ToDoPanel);
		ToDoPanel.setLayout(null);
		
		JLabel lblLogoLabel = new JLabel("");
		lblLogoLabel.setBounds(22, 17, 174, 135);
		backgroundPane.add(lblLogoLabel);
		lblLogoLabel.setIcon(new ImageIcon(IconLogo));
	}
}
