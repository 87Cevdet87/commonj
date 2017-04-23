package tr.com.sample.registration.test;

import java.io.File;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TestClient {

	public static void main(String[] args) {
		
		File[] listRoots = File.listRoots();
		System.out.println(listRoots[0].getPath());
	}

}
