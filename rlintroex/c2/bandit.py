import numpy as np
import matplotlib.pyplot as plt

from c2 import EpsilonGreedy
from c2 import Environment


games = 100
arm_count = 10
time_steps = 1000

steps = 100
env = Environment.Environment(arm_count=arm_count, arm_var=1.0)
avg_v = np.zeros((steps, ))
for j in range(games):
    e_greedy = EpsilonGreedy.EpsilonGreedy(low=0, high=1, steps=steps, arm_count=arm_count)
    for i in range(time_steps):
        actions = e_greedy.get_actions(arm_count)
        reward = env.compute_reward(actions)
        e_greedy.update_values(reward, actions)
        env.evolve()

    e, v = e_greedy.get_data_point()
    avg_v += v
    env.reset()


plt.plot(e, avg_v / games)
plt.show()
