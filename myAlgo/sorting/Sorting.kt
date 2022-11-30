package com.ssafy.sorting

class Sorting {
    val arr = intArrayOf(1, 5, 3, 2, 4)
    fun bubbleSort() {
        for (i in arr.indices) {
            for (j in 0 until arr.size - i - 1) {
                if (arr[j] > arr[j + 1]) {
                    val tmp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = tmp
                }
            }
        }
        println(arr.contentToString())
    }

    fun insertSort() {
        for (i in 1 until arr.size) {
            for (j in i downTo 1) {
                if (arr[j - 1] > arr[j]) {
                    val tmp = arr[j - 1]
                    arr[j - 1] = arr[j]
                    arr[j] = tmp
                }
            }
        }
        println(arr.contentToString())
    }

    fun selectionSort() {
        for (i in arr.indices) {
            var idx = i
            for (j in i + 1 until arr.size) {
                if (arr[idx] > arr[j]) {
                    idx = j
                }
            }
            val tmp = arr[idx]
            arr[idx] = arr[i]
            arr[i] = tmp
        }
        println(arr.contentToString())
    }

    fun quickSort(array: IntArray, low: Int, high: Int) {

        //low가 high 보다 크거나 같다면 정렬 할 원소가 1개 이하이므로 return
        if (low >= high) {
            return
        }

        val pivot = partition(array, low, high)
        quickSort(array, low, pivot - 1)
        quickSort(array, pivot + 1, high)

    }

    fun partition(array: IntArray, left: Int, right: Int): Int {

        var low = left
        var high = right
        //부분 배열의 왼쪽 요소를 pivot으로 설정
        val pivot = array[left]

        //low가 high보다 작을 때 까지만 반복
        while (low < high) {

            //array[high]가 pivot보다 크고 high가 low보다 클때 high 감소
            while (array[high] > pivot && low < high) {
                high--
            }

            //array[lpw]가 pivot보다 작거나 같고 high가 low보다 클때 low증가
            while (array[low] <= pivot && low < high) {
                low++
            }

            //교환 할 두 요소를 찾았으면 두 요소 swap
            val tmp = array[low]
            array[low] = array[high]
            array[high] = tmp
        }

        //마지막으로 맨 처음 pivot 설정했던 위치의 원소와 array[low]원소 스왑
        val tmp = array[left]
        array[left] = array[low]
        array[low] = tmp

        //두 요소가 교환되면 피벗 요소가 low 위치에 있으므로 low 반환환
        return low
    }

    fun mergeSort(arr: IntArray, sortedArr: IntArray, start: Int, end: Int) {

        //start == end는 부분배열이 1개 원소만 갖고 있으므로 return
        if (start == end) {
            return
        }

        val mid = (start + end) / 2

        mergeSort(arr, sortedArr, start, mid)
        mergeSort(arr, sortedArr, mid + 1, end)

        merge(arr, sortedArr, start, mid, end)

    }

    fun merge(arr: IntArray, sortedArr: IntArray, start: Int, mid: Int, end: Int) {

        var left = start
        var right = mid + 1
        var idx = start

        while (left <= mid && right <= end) {

            //왼쪽 부분배열 첫 번째 원소가 오른쪽 부분 배열 r번째 원소보다 작거나 같을 경우
            //왼쪽의 첫번째 원소를 새 배열을 넣고 1과 idx를 1 증가
            if (arr[left] <= arr[right]) {
                sortedArr[idx] = arr[left]
                idx++
                left++
            }
            //오른쪽 부분배열 r 번째 원소가 왼쪽 부분 배열 첫번째 원소보다 작거나 같을 경우
            //오른쪽의 r번째 원소를 새 배열을 넣고 r과 idx를 1 증가
            else {
                sortedArr[idx] = arr[right]
                idx++
                right++
            }
        }
        /*
         * 왼쪽 부분배열이 먼저 모두 새 배열에 채워졌을 경우 (l > mid)
         * 오른쪽 부분배열 원소가 아직 남아있을 경우
         * 오른쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
         */
        if (left > mid) {
            while (right <= end) {
                sortedArr[idx] = arr[right]
                idx++
                right++
            }

        }
        /*
        * 오른쪽 부분배열이 먼저 모두 새 배열에 채워졌을 경우 (right > end)
        * 왼쪽 부분배열 원소가 아직 남아있을 경우
        * 왼쪽 부분배열의 나머지 원소들을 새 배열에 채워준다.
        */
        else {
            while (left <= mid) {
                sortedArr[idx] = arr[left]
                idx++
                left++
            }
        }

        for (i in start..end) {
            arr[i] = sortedArr[i]
        }

    }

    fun heapSort(arr: IntArray) {

        val size = arr.size

        // 부모노드와 heaify과정에서 음수가 발생하면 잘못 된 참조가 발생하기 때문에
        // 원소가 1개이거나 0개일 경우는 정렬 할 필요가 없으므로 바로 함수를 종료한다.
        if (size < 2) {
            return
        }

        // 가장 마지막 노드의 부모 노드 인덱스
        val parentIdx = (size - 2) / 2

        // max heap 만들기
        for (i in parentIdx downTo 0) {
            // 부모노드(i값)을 1씩 줄이면서 heap 조건을 만족시키도록 재구성한다.
            heapify(arr, i, size - 1)
        }

        // 정렬 과정
        for (i in size - 1 downTo 1) {

            /*
			 *  root인 0번째 인덱스와 i번째 인덱스의 값을 교환해준 뒤
			 *  0 ~ (i-1) 까지의 부분트리에 대해 max heap을 만족하도록
			 *  재구성한다.
			 */

            val tmp = arr[0]
            arr[0] = arr[i]
            arr[i] = tmp
            heapify(arr, 0, i - 1)
        }

    }

    fun heapify(arr: IntArray, parentIdx: Int, lastIdx: Int) {

        var pIdx = parentIdx
        var leftChildIdx = 0
        var rightChildIdx = 0
        var largestIdx = 0


        /*
	     * 현재 부모 인덱스의 자식 노드 인덱스가
	     * 마지막 인덱스를 넘지 않을 때 까지 반복한다.
	     *
	     * 이 때 왼쪽 자식 노드를 기준으로 해야 한다.
	     * 오른쪽 자식 노드를 기준으로 범위를 검사하게 되면
	     * 마지막 부모 인덱스가 왼쪽 자식만 갖고 있을 경우
	     * 왼쪽 자식노드와는 비교 및 교환을 할 수 없기 때문이다.
	     */

        while ((pIdx * 2) + 1 <= lastIdx) {
            leftChildIdx = (pIdx * 2) + 1
            rightChildIdx = (pIdx * 2) + 2
            largestIdx = pIdx

            /*
	         * left child node와 비교
	         * (범위는 while문에서 검사했으므로 별도 검사 필요 없음)
	         */
            if (arr[leftChildIdx] > arr[largestIdx]) {
                largestIdx = leftChildIdx
            }

            /*
	         * right child node와 비교
	         * right child node는 범위를 검사해주어야한다.
	         */
            if (rightChildIdx <= lastIdx && arr[rightChildIdx] > arr[largestIdx]) {
                largestIdx = rightChildIdx;
            }

            /*
	         * 교환이 발생했을 경우 두 원소를 교체 한 후
	         * 교환이 된 자식노드를 부모 노드가 되도록 교체한다.
	         */
            if (largestIdx != pIdx) {

                val tmp = arr[pIdx]
                arr[pIdx] = arr[largestIdx]
                arr[largestIdx] = tmp

                pIdx = largestIdx
            } else {
                return;
            }
        }

    }

    fun countingSort(arr: IntArray, res: IntArray) {

        val counting = IntArray(arr.maxOrNull()!! + 1)

        // 첫 번째
        for (i in arr.indices) {
            counting[arr[i]]++
        }

        //두 번째
        for (i in 1 until counting.size) {
            counting[i] += counting[i - 1]
        }

        //세 번째
        for (i in arr.size - 1 downTo 0) {
            val value = arr[i]
            counting[value]--
            res[counting[value]] = value
        }


    }


}

fun main() {
    val arr = intArrayOf(1, 5, 3, 2, 4)

//    Sorting().bubbleSort()
//    Sorting().insertSort()
//    Sorting().selectionSort()


//    Sorting().quickSort(arr,0,arr.size-1)
//    println(arr.contentToString())

//    val sortedArr = IntArray(arr.size)
//    Sorting().mergeSort(arr, sortedArr, 0, arr.size - 1)
//    println(arr.contentToString())

//    Sorting().heapSort(arr)
//    println(arr.contentToString())

    val res = IntArray(arr.size)
    Sorting().countingSort(arr, res)
    println(res.contentToString())


}