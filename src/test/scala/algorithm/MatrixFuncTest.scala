// Wei Chen - Matrix Function Test
// 2016-06-03

import org.scalatest.FunSuite
import ght.mi.algorithm.MatrixFunc._

class MatrixFuncSuite extends FunSuite {
    def rint4(x: Double): Double = Math.rint(x * 10000) / 10000
    def arrrint4(x: Array[Double]): Array[Double] = x.map(rint4(_))
    val data = Array(
        Array(1,2,1),
        Array(2,1,6),
        Array(1,2,6),
        Array(0,3,1),
        Array(1,3,1)
    ).map(_.map(_.toDouble))
    val datasize = data.size
    val featuresize = data.head.size

    test("MatrixFunc Test : Array Equality"){
        assert(!arrayequal(data(0), data(1)))
        assert(arrayequal(data(0), Array(1.0,2.0,1.0)))
    }

    test("MatrixFunc Test : Array Sum"){
        assert(arrayequal(arraysum(data(0),data(1)), Array(3.0,3.0,7.0)))
    }

    test("MatrixFunc Test : Array Minus"){
        assert(arrayequal(arrayminus(data(0),data(1)), Array(-1.0,1.0,-5.0)))
    }

    test("MatrixFunc Test : Array Minus Square"){
        assert(arrayequal(arrayminussquare(data(0),data(1)), Array(1.0,1.0,25.0)))
    }

    test("MatrixFunc Test : Array Multiplication"){
        assert(arrayequal(arraymultiply(data(0),data(1)), Array(2.0,2.0,6.0)))
    }

    test("MatrixFunc Test : Array Devision"){
        assert(arrayequal(arraydevide(data(0),data(1)), Array(0.5,2.0,1.0/6.0)))
        assert(arrayequal(arraydevide(data(2),data(3)), Array(0.0,2.0/3.0,6.0)))
    }

    test("MatrixFunc Test : Matrix Equality"){
        val temp = Array(Array(0.1, 0.2), Array(0.0, 1.0));
        assert(matrixequal(temp, temp))
    }

    test("MatrixFunc Test : Matrix Sum"){
        assert(arrayequal(matrixaccumulate(data), Array(5.0,11.0,15.0)))
    }

    val normalizeddata = normalize(data)
    test("MatrixFunc Test : Normalization"){
        assert(arrayequal(normalizeddata._1, Array(1.0,2.2,3.0)))
        assert(arrayequal(arrrint4(normalizeddata._2), Array(0.6325, 0.7483, 2.4495)))
        assert(arrayequal(arrrint4(normalizeddata._3(0)), Array(0.0, -0.2673, -0.8165)))
    }

    test("MatrixFunc Test : De-Normalization"){
        val denormalizeddata = denormalize(normalizeddata._3, normalizeddata._1, normalizeddata._2)
        assert(arrayequal(denormalizeddata(0), Array(1.0,2.0,1.0)))
    }

    val covariancedata = covariance(data)
    test("MatrixFunc Test : Covariance"){
        assert(arrayequal(covariancedata(0), Array(0.4,-0.4,1.0)))
    }

    test("MatrixFunc Test : Sub Matrix"){
        val submatrixdata = submatrix(data, 0, 0)
        assert(arrayequal(submatrixdata(0), Array(1.0,6.0)))
    }

    test("MatrixFunc Test : Determinant"){
        val determinantdata = determinant(covariancedata)
        assert(rint4(determinantdata) == 0.16)
    }

    val inversedata = inverse(covariancedata)
    test("MatrixFunc Test : Inverse"){
        assert(arrayequal(arrrint4(inversedata(0)), Array(8.75, 6.25, 0.0)))
    }

    test("MatrixFunc Test : Mahalanobis ^ 2"){
        val mahalanobisdistance2 = mahalanobis2(data(0), normalizeddata._1, covariancedata)
        assert(rint4(mahalanobisdistance2) == 2.75)
    }

    test("MatrixFunc Test : Gaussian Probability"){
        val gaussianprobabilitydata = gaussianprobability(data(0), normalizeddata._1, covariancedata)
        assert(rint4(gaussianprobabilitydata) == 0.0401)
    }

    val dotdata = matrixdot(covariancedata, inversedata)
    test("MatrixFunc Test : Matrix Dot"){
        assert(arrayequal(arrrint4(matrixaccumulate(dotdata)), Array(1.0, 1.0, 1.0)))
    }
}