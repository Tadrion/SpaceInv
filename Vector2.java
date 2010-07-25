public class Vector2 {
	public float x,y;
	
	public Vector2() {
		x = 0;
		y = 0;
	}
	
	public Vector2(float c1, float c2) {
		x = c1;
		y = c2;
	}

	public static Vector2 add(Vector2 a, Vector2 b) {
		return new Vector2(a.x + b.x, a.y + b.y);
	}


	public static Vector2 sub(Vector2 a, Vector2 b) {
		return new Vector2(a.x - b.x, a.y - b.y);
	}
	
	public static Vector2 mult(float f, Vector2 a) {
		return new Vector2(f * a.x, f * a.y);
	
	}

	public float length() {
		return (float) Math.sqrt(x*x + y*y);
	}
	
	public Vector2 normalisedCopy() {
		float len = (float)Math.sqrt(x*x + y*y);
		if (len > 0.0f) {
			float lenInv = 1.0f/len;
			return new Vector2(x*lenInv, y*lenInv);
		}
		else
			return this;
	}
	
}
