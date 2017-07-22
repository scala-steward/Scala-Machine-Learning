// Wei Chen - K Mean Cluster Test
// 2016-06-04

import org.scalatest.FunSuite
import ght.mi.TestData._
import ght.mi.general.MatrixFunc._
import ght.mi.algorithm.KMean

class KMeanSuite extends FunSuite {

    val km = new KMean()
    test("KMean Test : Clustering Tiny Data") {
        val result = km.cluster(UNLABELED_TINY_DATA, 2, 100)
        assert(arrayequal(result, LABEL_TINY_DATA))
    }

    test("KMean Test : Clustering Small Data") {
        val result = km.cluster(UNLABELED_SMALL_DATA, 2, 100)
        assert(arrayequal(result, LABEL_SMALL_DATA))
    }

    test("KMean Test : Clustering Large Data - WRONG") {
        val result = km.cluster(UNLABELED_LARGE_DATA, 2, 100)
        assert(!arrayequal(result, LABEL_LARGE_DATA))
    }
}