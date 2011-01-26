/**
 *  http://www.nowamagic.net/algorithm/algorithm_EfficacyOfFunctionSqrt.php
 *  http://stackoverflow.com/questions/856228/how-can-i-improve-this-square-root-method
 */

#include <iostream>
#include <math.h>
#include <stdlib.h>
#include <assert.h>

using namespace std;

// const double precision = 0.000000001;
const double precision = 0.001;

double binarySqrt(double n) {
  assert(n>=0);

  double low, high, m;
  if (n>1) {
    low = 1;
    high = n;
  }
  else {
    low = n;
    high = 1;
  }
  m = (low + high)/2;
  int count=0;
  while(fabs(m*m-n)>precision) {
    if (m*m > n) {
      high = m;
    }
    else {
      low = m;
    }
    m = (low + high)/2;
    count++;
  }
  cout<<"binary ran "<<count<<" times"<<endl;
  return m;
}

//////////////////////////////////////////////////////////////
// NEWTON APPROACH
//
// TIME COMPLEXITY Analysis for newton method
// http://en.citizendium.org/wiki/Newton's_method

/**
 * 1. sqrt
 * http://www.macs.hw.ac.uk/~pjbk/pathways/cpp1/node124.html 
 */

// guess r by taking the average of r and n/r
// which is (r+n/r)/2.0
double newtonSqrt1(double n) {
  assert(n>=0);
  double r=n/2; // the best initial guess is 2^(ceil ( (num of bits of n)/2 )) http://stackoverflow.com/questions/1623375/writing-your-own-square-root-function
  int i=0;
  while(fabs(r*r-n)>=precision) {
    ++i;
    r = (r + n/r)/2;
  }
  cout<<"newton sqrt1 ran "<<i<<" times"<<endl;
  return r;
}

double newtonSqrt2(double n) {
  assert(n>=0);
  double r, last;
  r=n;
  int i=0;
  do {
    ++i;
    last = r;
    r = (r + n/r)/2;
  } while (fabs(r-last)>precision);
  cout<<"newton sqrt2 ran "<<i<<" times"<<endl;
  return r;
}

int newton(int n) {
  int r = n/2;
  while(fabs(r*r-n)>precision) {
    double o = r;
    r = (r + n/r)/2;
  }
}

/**
 * 2. cubic root 
 */
double newtowCubicRoot(double x) {
    if(x==0) return 0;
    double x0=1;
    double x1=x0*2/3+x/3/x0/x0;
    while(abs(x1-x0) > precision) {
       x0=x1;
       x1=x0*2/3+x/3/x0/x0;
    }
    return x1;
}

int main() {
  double n;
  cin>>n;
  cout<<sqrt(n)<<endl;
  cout<<binarySqrt(n)<<endl;
  cout<<newtonSqrt1(n)<<endl;
  cout<<newtonSqrt2(n)<<endl;
  return 1;
}


