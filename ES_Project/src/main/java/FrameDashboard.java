import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class FrameDashboard extends JFrame {

	private JPanel backgroundPane;
	private Image IconLogo = new ImageIcon(FrameLogin.class.getResource("Res/logo.png")).getImage().getScaledInstance(150,150,Image.SCALE_SMOOTH);
	private Image home = new ImageIcon(FrameLogin.class.getResource("Res/home.png")).getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
	private PanelHome PanelHome2;
	private ImportPanel ImportPanel2;
	private ViewPanel ViewPanel2;
	private ExportPanel ExportPanel2;
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
		setLocationRelativeTo(null);
		setBounds(100, 100, 807, 550);
		setUndecorated(false);
		backgroundPane = new JPanel();
		backgroundPane.setBackground(new Color(255, 255, 255));
		backgroundPane.setBorder(new LineBorder(new Color(0, 128, 128)));

		setContentPane(backgroundPane);
		backgroundPane.setLayout(null);
		
		PanelHome2 = new PanelHome();
		ImportPanel2= new ImportPanel();
		ViewPanel2 = new ViewPanel();
		ExportPanel2 = new ExportPanel();
		
		//Menu Panel
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBounds(0, 160, 219, 390);
		MenuPanel.setBackground(new Color(0, 128, 128));
		backgroundPane.add(MenuPanel);
		MenuPanel.setLayout(null);
		
		// Home Button
		JPanel panelHome = new JPanel();
		//panelHome.addMouseListener(new PanelButtomMouseAdapter(PanelHome2));
		panelHome.addMouseListener(new PanelButtomMouseAdapter(panelHome) {
			@Override
			public void mouseClicked(MouseEvent e){
				menuClicked(PanelHome2);
			}
		});
		panelHome.setBorder(new LineBorder(new Color(255, 255, 255)));
		panelHome.setForeground(new Color(255, 255, 255));
		panelHome.setToolTipText("");
		panelHome.setBackground(new Color(0, 128, 128));
		panelHome.setBounds(0, 35, 219, 47);
		MenuPanel.add(panelHome);
		panelHome.setLayout(null);
		
		// Text Home Button
		JLabel lblLabelHome = new JLabel("Home");
		lblLabelHome.setBounds(83, 6, 65, 35);
		lblLabelHome.setForeground(new Color(255, 255, 255));
		lblLabelHome.setFont(new Font("Dialog", Font.PLAIN, 16));
		panelHome.add(lblLabelHome);
		
		// Import Button
		JPanel ImportPanel = new JPanel();
		ImportPanel.addMouseListener(new PanelButtomMouseAdapter(ImportPanel) {
			@Override
			public void mouseClicked(MouseEvent e){
				menuClicked(ImportPanel2);
			}
		});
		ImportPanel.setBorder(new LineBorder(new Color(255, 255, 255)));
		ImportPanel.setToolTipText("");
		ImportPanel.setForeground(Color.WHITE);
		ImportPanel.setBackground(new Color(0, 128, 128));
		ImportPanel.setBounds(0, 81, 219, 47);
		MenuPanel.add(ImportPanel);
		ImportPanel.setLayout(null);
		
		// Text Import Button
		JLabel lblNewLabelImport = new JLabel("Import");
		lblNewLabelImport.setForeground(Color.WHITE);
		lblNewLabelImport.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabelImport.setBounds(73, 6, 65, 35);
		ImportPanel.add(lblNewLabelImport);
		
		JPanel ViewPanel = new JPanel();
		ViewPanel.addMouseListener(new PanelButtomMouseAdapter(ViewPanel) {
			@Override
			public void mouseClicked(MouseEvent e){
				menuClicked(ViewPanel2);
			}
		});
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
		ExportPanel.addMouseListener(new PanelButtomMouseAdapter(ExportPanel) {
			@Override
			public void mouseClicked(MouseEvent e){
				menuClicked(ExportPanel2);
			}
		});
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
		
		JPanel signOutPane = new JPanel();
		signOutPane.addMouseListener(new PanelButtomMouseAdapter(signOutPane) {
			@Override
			public void mouseClicked(MouseEvent e){
				if(JOptionPane.showConfirmDialog(null, "Are you sure that you want to sign out?") == 0) {
					FrameLogin frameLogin = new FrameLogin();
					frameLogin.setVisible(true);
					FrameDashboard.this.setVisible(false);
					FrameDashboard.this.dispose();
				}
				
			}
		});
		signOutPane.setBorder(new LineBorder(new Color(255, 255, 255)));
		signOutPane.setToolTipText("");
		signOutPane.setForeground(Color.WHITE);
		signOutPane.setBackground(new Color(0, 128, 128));
		signOutPane.setBounds(0, 218, 219, 47);
		MenuPanel.add(signOutPane);
		signOutPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SIGN OUT");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(63, 6, 83, 35);
		signOutPane.add(lblNewLabel);
		
		JLabel lblLogoLabel = new JLabel("");
		lblLogoLabel.setBounds(22, 17, 174, 135);
		backgroundPane.add(lblLogoLabel);
		lblLogoLabel.setIcon(new ImageIcon(IconLogo));
		
		JPanel panelMain = new JPanel();
		panelMain.setBackground(new Color(255, 255, 255));
		panelMain.setBounds(235, 67, 551, 433);
		backgroundPane.add(panelMain);
		panelMain.setLayout(null);
		
		panelMain.add(PanelHome2);
		panelMain.add(ImportPanel2);
		panelMain.add(ExportPanel2);
		panelMain.add(ViewPanel2);
		
		menuClicked(PanelHome2);
	}
	
	public void menuClicked(JPanel panel) {
		PanelHome2.setVisible(false);
		ImportPanel2.setVisible(false);
		ViewPanel2.setVisible(false);
		ExportPanel2.setVisible(false);
		
		panel.setVisible(true);
		
	}
	
	private class PanelButtomMouseAdapter extends MouseAdapter{
		JPanel panel;
		public PanelButtomMouseAdapter(JPanel panel) {
			this.panel = panel;
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setForeground(new Color(112, 128, 144));
		
		}
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setForeground(new Color(47, 79, 79));
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setForeground(new Color(60, 179, 113));
			
			
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setForeground(new Color(112, 128, 144));
			
		}
	}
}
