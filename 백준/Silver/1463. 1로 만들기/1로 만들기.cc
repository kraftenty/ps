#include <iostream>
#include <cstring>

#define fio ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL)
using namespace std;

int n;
int cache[1000001];

int DP(int num) {
    if (num == 1)
        return 0;

    if (cache[num] != -1)
        return cache[num];

    int res = DP(num-1) +1;  // 빼기1
    if (num % 3 == 0)
        res = min(res, DP(num/3)+1); //나누기3
    if (num % 2 == 0)
        res = min(res, DP(num/2)+1); //나누기2

    cache[num]=res;
    return cache[num];
}

int main() {
    fio;
    cin >> n;
    memset(cache, -1, sizeof(cache));
    cout << DP(n);
    return 0;
}