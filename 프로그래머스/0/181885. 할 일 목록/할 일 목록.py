def solution(todo_list, finished):
    return [a for a, b in zip(todo_list, finished) if not b]    