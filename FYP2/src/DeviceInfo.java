import java.io.Serializable;
import java.sql.Timestamp;
public class DeviceInfo implements Serializable {
	
	/**
	 * 	`
	 */
	private static final long serialVersionUID = 1L;
	public String ID;
	public float longitude,latitude;
	public int gas_sensor,temperature_sensor;
	public Timestamp send_time;
	public DeviceInfo()
	{
		
	}

}

