package com.expertgroup.cubesum.helper;

import org.springframework.stereotype.Component;

@Component
public class CubeSumHelper {
	private long[][][] cube;
	private long[][][] numCube;
	private int dimensions = 0;

	public CubeSumHelper() {
	}

	public CubeSumHelper(int dimensions) {
		if (dimensions == 0) return;
		this.dimensions = dimensions;
		cube = new long[dimensions+1][dimensions+1][dimensions+1];
		numCube = new long[dimensions][dimensions][dimensions];
	}

	public void update(int x, int y, int z, int value) {
		long delta = value - numCube[x][y][z];
		numCube[x][y][z] = value;
		for (int i = x + 1; i <= dimensions; i += i & (-i)) {
			for (int j = y + 1; j <= dimensions; j += j & (-j)) {
				for (int k = z + 1; k <= dimensions; k += k & (-k)) {
					cube[i][j][k] +=  delta;
				}
			}
		}
	}
	
	private long sum(int x, int y, int z) {
		long sum = 0l;
		for (int i = x; i > 0; i -= i & (-i)) {
			for (int j = y; j > 0; j -= j & (-j)) {
				for (int k = z; k > 0; k -= k & (-k)) {
					sum += cube[i][j][k];
				}
			}
		}
		return sum;
	}

	public long query(int x1, int y1, int z1, int x2, int y2, int z2) {
		return sum(x2+1,y2+1,z2+1) - sum(x1,y1,z1) - sum(x1,y2+1,z2+1) - sum(x2+1,y1,z2+1) - sum(x2+1,y2+1,z1) + sum(x1,y1,z2+1) + sum(x1,y2+1,z1) + sum(x2+1,y1,z1);
	}

	public String process(String[] commands) {
		String result = "";
		CubeSumHelper solution = null;
		int testCases = Integer.parseInt(commands[0].trim());
		int numCases = 0;
		for (int i = 1; i < commands.length; i++) {			
			String line = commands[i].trim();
			String[] lineParts = line.split(" ");

			if (lineParts[0].equals("UPDATE")) {
				solution.update(Integer.parseInt(lineParts[1])-1, Integer.parseInt(lineParts[2])-1, Integer.parseInt(lineParts[3])-1, Integer.parseInt(lineParts[4]));
			} else if (lineParts[0].equals("QUERY")) {
				result += solution.query(Integer.parseInt(lineParts[1])-1, Integer.parseInt(lineParts[2])-1, Integer.parseInt(lineParts[3])-1, Integer.parseInt(lineParts[4])-1, Integer.parseInt(lineParts[5])-1, Integer.parseInt(lineParts[6])-1);
				result += "<br />";
			} else if(lineParts.length >= 2) {
				numCases++;
				if(numCases > testCases) {
					break;
				}
				int dimensions = Integer.parseInt(lineParts[0]);
				solution = new CubeSumHelper(dimensions);
			}
		}
		return result;
	}
}
