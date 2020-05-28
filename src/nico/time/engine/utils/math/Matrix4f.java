package nico.time.engine.utils.math;

import java.nio.FloatBuffer;

import nico.time.engine.input.WindowSize;

public class Matrix4f {

	public float m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33;
	
	/**Create a matrix and set it to be the identity matrix.
	 */
	public Matrix4f() {
		this.m00 = 1.0f; this.m01 = 0.0f; this.m02 = 0.0f; this.m03 = 0.0f;
		this.m10 = 0.0f; this.m11 = 1.0f; this.m12 = 0.0f; this.m13 = 0.0f;
		this.m20 = 0.0f; this.m21 = 0.0f; this.m22 = 1.0f; this.m23 = 0.0f;
		this.m30 = 0.0f; this.m31 = 0.0f; this.m32 = 0.0f; this.m33 = 1.0f;
	}
	
	/**Store this matrix in a float buffer <br>
	 * The matrix is stored in column major (openGL) order. <br>
	 * @param buf - The buffer to store this matrix in
	 */
	public void store(FloatBuffer buf) {
		buf.put(m00); buf.put(m01); buf.put(m02); buf.put(m03);
		buf.put(m10); buf.put(m11); buf.put(m12); buf.put(m13);
		buf.put(m20); buf.put(m21); buf.put(m22); buf.put(m23);
		buf.put(m30); buf.put(m31); buf.put(m32); buf.put(m33);
	}
	
	/**Translate the matrix
	 * @param vec - The vector to translate by
	 */
	public Matrix4f translate(Vector3f vec) {
		this.m30 += this.m00 * vec.x + this.m10 * vec.y + this.m20 * vec.z;
		this.m31 += this.m01 * vec.x + this.m11 * vec.y + this.m21 * vec.z;
		this.m32 += this.m02 * vec.x + this.m12 * vec.y + this.m22 * vec.z;
		this.m33 += this.m03 * vec.x + this.m13 * vec.y + this.m23 * vec.z;
		return this;
	}
	
	/**Translate the matrix
	 * @param vec - The vector to translate by
	 */
	public Matrix4f translate(Vector2f vec) {
		this.m30 += this.m00 * vec.x + this.m10 * vec.y;
		this.m31 += this.m01 * vec.x + this.m11 * vec.y;
		this.m32 += this.m02 * vec.x + this.m12 * vec.y;
		this.m33 += this.m03 * vec.x + this.m13 * vec.y;
		return this;
	}
	
	/**Scales the matrix
	 * @param vec The vector to scale by
	 */
	public Matrix4f scale(Vector3f vec) {
		this.m00 = this.m00 * vec.x;
		this.m01 = this.m01 * vec.x;
		this.m02 = this.m02 * vec.x;
		this.m03 = this.m03 * vec.x;
		this.m10 = this.m10 * vec.y;
		this.m11 = this.m11 * vec.y;
		this.m12 = this.m12 * vec.y;
		this.m13 = this.m13 * vec.y;
		this.m20 = this.m20 * vec.z;
		this.m21 = this.m21 * vec.z;
		this.m22 = this.m22 * vec.z;
		this.m23 = this.m23 * vec.z;
		return this;
	}
	
	/**Scales the matrix
	 * @param vec The vector to scale by
	 */
	public Matrix4f scale(Vector2f vec) {
		this.m00 = this.m00 * vec.x;
		this.m01 = this.m01 * vec.x;
		this.m02 = this.m02 * vec.x;
		this.m03 = this.m03 * vec.x;
		this.m10 = this.m10 * vec.y;
		this.m11 = this.m11 * vec.y;
		this.m12 = this.m12 * vec.y;
		this.m13 = this.m13 * vec.y;
		return this;
	}
	
	public float determinant() {
		float f = m00 * ((m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21 * m32) - m13 * m22 * m31 - m11 * m23 * m32 - m12 * m21 * m33);
		f -= m01 * ((m10 * m22 * m33 + m12 * m23 * m30 + m13 * m20 * m32) - m13 * m22 * m30 - m10 * m23 * m32 - m12 * m20 * m33);
		f += m02 * ((m10 * m21 * m33 + m11 * m23 * m30 + m13 * m20 * m31) - m13 * m21 * m30 - m10 * m23 * m31 - m11 * m20 * m33);
		f -= m03 * ((m10 * m21 * m32 + m11 * m22 * m30 + m12 * m20 * m31) - m12 * m21 * m30 - m10 * m22 * m31 - m11 * m20 * m32);
		return f;
	}
	
	private float determinant3x3(float t00, float t01, float t02, float t10, float t11, float t12, float t20, float t21, float t22) {
		return t00 * (t11 * t22 - t12 * t21) + t01 * (t12 * t20 - t10 * t22) + t02 * (t10 * t21 - t11 * t20);
	}
	
	public Matrix4f invert() {
		float determinant = this.determinant();

		if (determinant != 0) {
			float determinant_inv = 1.0f / determinant;

			// first row
			float t00 = determinant3x3(this.m11, this.m12, this.m13, this.m21, this.m22, this.m23, this.m31, this.m32, this.m33);
			float t01 = -determinant3x3(this.m10, this.m12, this.m13, this.m20, this.m22, this.m23, this.m30, this.m32, this.m33);
			float t02 = determinant3x3(this.m10, this.m11, this.m13, this.m20, this.m21, this.m23, this.m30, this.m31, this.m33);
			float t03 = -determinant3x3(this.m10, this.m11, this.m12, this.m20, this.m21, this.m22, this.m30, this.m31, this.m32);
			// second row
			float t10 = -determinant3x3(this.m01, this.m02, this.m03, this.m21, this.m22, this.m23, this.m31, this.m32, this.m33);
			float t11 = determinant3x3(this.m00, this.m02, this.m03, this.m20, this.m22, this.m23, this.m30, this.m32, this.m33);
			float t12 = -determinant3x3(this.m00, this.m01, this.m03, this.m20, this.m21, this.m23, this.m30, this.m31, this.m33);
			float t13 = determinant3x3(this.m00, this.m01, this.m02, this.m20, this.m21, this.m22, this.m30, this.m31, this.m32);
			// third row
			float t20 = determinant3x3(this.m01, this.m02, this.m03, this.m11, this.m12, this.m13, this.m31, this.m32, this.m33);
			float t21 = -determinant3x3(this.m00, this.m02, this.m03, this.m10, this.m12, this.m13, this.m30, this.m32, this.m33);
			float t22 = determinant3x3(this.m00, this.m01, this.m03, this.m10, this.m11, this.m13, this.m30, this.m31, this.m33);
			float t23 = -determinant3x3(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m30, this.m31, this.m32);
			// fourth row
			float t30 = -determinant3x3(this.m01, this.m02, this.m03, this.m11, this.m12, this.m13, this.m21, this.m22, this.m23);
			float t31 = determinant3x3(this.m00, this.m02, this.m03, this.m10, this.m12, this.m13, this.m20, this.m22, this.m23);
			float t32 = -determinant3x3(this.m00, this.m01, this.m03, this.m10, this.m11, this.m13, this.m20, this.m21, this.m23);
			float t33 = determinant3x3(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22);

			// transpose and divide by the determinant
			this.m00 = t00*determinant_inv;
			this.m11 = t11*determinant_inv;
			this.m22 = t22*determinant_inv;
			this.m33 = t33*determinant_inv;
			this.m01 = t10*determinant_inv;
			this.m10 = t01*determinant_inv;
			this.m20 = t02*determinant_inv;
			this.m02 = t20*determinant_inv;
			this.m12 = t21*determinant_inv;
			this.m21 = t12*determinant_inv;
			this.m03 = t30*determinant_inv;
			this.m30 = t03*determinant_inv;
			this.m13 = t31*determinant_inv;
			this.m31 = t13*determinant_inv;
			this.m32 = t23*determinant_inv;
			this.m23 = t32*determinant_inv;
		}
		return this;
	}
	
	public Vector4f transform(Vector4f vector) {
		float x = this.m00 * vector.x + this.m10 * vector.y + this.m20 * vector.z + this.m30 * vector.w;
		float y = this.m01 * vector.x + this.m11 * vector.y + this.m21 * vector.z + this.m31 * vector.w;
		float z = this.m02 * vector.x + this.m12 * vector.y + this.m22 * vector.z + this.m32 * vector.w;
		float w = this.m03 * vector.x + this.m13 * vector.y + this.m23 * vector.z + this.m33 * vector.w;
		return new Vector4f(x, y, z, w);
	}
	
	/**Create a transformation matrix <br>
	 * Used in rendering to load a model's position and scale into the shader
	 * @param translation - The 2D vector representing the translation
	 * @param scale - The scale of the model
	 */
	public static Matrix4f createTransformationMatrix(Vector3f translation, Vector2f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.translate(translation);
		matrix.scale(scale);
		return matrix;
	}
	
	/**Creates a projection matrix
	 * @param fov - Field of view
	 * @param zNear - Nearest distance the camera can see
	 * @param zFar - Furthest distance the camera can see
	 */
	public static Matrix4f createProjectionMatrix(float fov, float zNear, float zFar) {
		float aspectRatio = (float) WindowSize.width / (float) WindowSize.height;
		float yScale = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))) * aspectRatio);
		float xScale = yScale / aspectRatio;
		float frustumLength = zFar - zNear;
		
		Matrix4f projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = xScale;
		projectionMatrix.m11 = yScale;
		projectionMatrix.m22 = -((zFar + zNear) / frustumLength);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * zNear * zFar) / frustumLength);
		projectionMatrix.m33 = 0;
		
		return projectionMatrix;
	}
	
	/**Creates a 2D orthogonal projection matrix <br>
	 * Used in guis
	 * @param left - -window width / 2
	 * @param right - window width / 2
	 * @param bottom - window height / 2
	 * @param top - -window height / 2
	 * @param near - Near plane
	 * @param far - Far plane
	 */
	public static Matrix4f createProjectionMatrix(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f ortho = new Matrix4f();
		ortho.m00 = 2.0f / (right - left);
		ortho.m11 = 2.0f / (top - bottom);
		ortho.m22 = -2.0f / (far - near);
		ortho.m30 = -(right + left) / (right - left);
		ortho.m31 = -(top + bottom) / (top - bottom);
		ortho.m32 = -(far + near) / (far - near);
		ortho.scale(new Vector2f(100.0f, -100.0f));
		return ortho;
    }
	
	/**Creates a view matrix
	 * @param camera - Used for view matrix
	 */
	public static Matrix4f createViewMatrix(Vector3f cameraPos) {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.translate(new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z));
		return viewMatrix;
	}
}
