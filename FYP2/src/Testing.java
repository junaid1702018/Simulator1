import java.util.UUID;
import java.util.Random;

public interface Testing {
	public static void main(String[] args) {
		int Number_Of_Devices = 10, i;
		Random rand = new Random();
		for (i = 0; i < Number_Of_Devices; i++) {
			String id1 = UUID.randomUUID().toString();
			float longitude = rand.nextFloat() * 100;
			float latitude = rand.nextFloat() * 100;
			SGLD device = new SGLD(id1, longitude, latitude);
			device.start();
		}
	}

}
