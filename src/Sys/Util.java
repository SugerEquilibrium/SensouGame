package Sys;

public class Util {
	
	//方向番号を受け取り、それに対応する相対的な座標系のベクトルの配列を返します
	//方向番号は時計回りに0時方向から0で始まり7で終わる
	public static int[] directionVector(int direction) {
		int[] v = {0, 0};
		switch(direction) {
		case 0:
			v[1] -= 1;
			break;
		case 1:
			v[0] += 1;
			v[1] -= 1;
			break;
		case 2:
			v[0] +=1;
			break;
		case 3:
			v[0] += 1;
			v[1] += 1;
			break;
		case 4:
			v[1] += 1;
			break;
		case 5:
			v[0] -= 1;
			v[1] += 1;
			break;
		case 6:
			v[0] -= 1;
			break;
		case 7:
			v[0] -= 1;
			v[1] -= 1;
			break;
		}
		return v;
	}
}
