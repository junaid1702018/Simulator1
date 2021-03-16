import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLayeredPane;

public class Server_GUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model;
	private JScrollPane scrollPane;
	private JPanel panel;
	final Object[] row;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server_GUI frame = new Server_GUI();
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
	public Server_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 432, 261);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(176, 196, 222));
		model = new DefaultTableModel();
		Object[] column= {"ID","Longitude","Latitude","Temperature","Gas","Send Time","Recieve Time"};
		row=new Object[7];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
        
	}
	public void add_data(String id,float longitude,float latitude,int temperature_sensor,int gas_sensor,Timestamp send_time, Timestamp recieve_time)
	{	
		row[0]=id;
		row[1]=Float.toString(longitude);
        row[2]=Float.toString(latitude);
        row[3]=Integer.toString(temperature_sensor);
        row[4]=Integer.toString(gas_sensor);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        row[5]=formatter.format(send_time.toLocalDateTime());
        row[6]=formatter.format(recieve_time.toLocalDateTime());
        model.addRow(row);
	}
}
