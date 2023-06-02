import sys

alphabets = ["dz=", "c=", "c-", "d-", "lj", "nj", "s=", "z="]
word = sys.stdin.readline().rstrip()
for alphabet in alphabets:
    word = word.replace(alphabet, "#")
print(len(word))


