import sys

input = sys.stdin.readline

male_lower, male_taller = [], []
female_lower, female_taller = [], []

N = int(input())
males = list(map(int, input().rstrip().split()))
for male in males:
    if male > 0:
        male_taller.append(male)
    else:
        male_lower.append(-male)
females = list(map(int, input().rstrip().split()))
for female in females:
    if female > 0:
        female_taller.append(female)
    else:
        female_lower.append(-female)

male_lower.sort()
male_taller.sort()
female_lower.sort()
female_taller.sort()

result = 0
i, j = 0, 0
while i < len(male_taller) and j < len(female_lower):
    if male_taller[i] < female_lower[j]:
        result += 1
        i += 1
        j += 1
    else:
        j += 1

i, j = 0, 0
while i < len(male_lower) and j < len(female_taller):
    if male_lower[i] > female_taller[j]:
        result += 1
        i += 1
        j += 1
    else:
        i += 1

print(result)